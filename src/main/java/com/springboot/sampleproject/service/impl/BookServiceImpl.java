package com.springboot.sampleproject.service.impl;

import com.springboot.sampleproject.config.LargeDataRowHandler;
import com.springboot.sampleproject.model.dao.BookMapper;
import com.springboot.sampleproject.model.dto.BookDto;
import com.springboot.sampleproject.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> getAllDataList() {
        return bookMapper.getAllDataList();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void selectData(Map<String, Object> param, LargeDataRowHandler largeDataRowHandler) {
        largeDataRowHandler.startArray();
        bookMapper.selectData(param,largeDataRowHandler);
        largeDataRowHandler.endArray();
        largeDataRowHandler.close();
    }

    @Async
    @Override
    public void saveBook(int i) {
        try {
//            Map input = new HashMap();
            Thread.sleep(10);

            Map param = new HashMap();
            param.put("idx",Long.valueOf(i));
            param.put("name","book " + i);
            param.put("desc","desc " + i);

            bookMapper.saveBook(param);
            log.info("[AsyncMethod]" + "-" + i + " " +  "Insert Data!");
        } catch (TaskRejectedException | InterruptedException e) {
            e.printStackTrace();
        }


   }

    @Override
    public void select(@SuppressWarnings("rawtypes") Map param, LargeDataRowHandler largeDataRowHandler) {
        largeDataRowHandler.startArray();
        bookMapper.selectLargeData(param,largeDataRowHandler);
        largeDataRowHandler.endArray();
        largeDataRowHandler.close();
    }
}
