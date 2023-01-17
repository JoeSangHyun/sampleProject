package com.springboot.sampleproject.service.impl;

import com.springboot.sampleproject.config.LargeDataRowHandler;
import com.springboot.sampleproject.config.SelectDataHandler;
import com.springboot.sampleproject.config.impl.SelectDataRowHandler;
import com.springboot.sampleproject.model.dao.BookMapper;
import com.springboot.sampleproject.model.dto.BookDto;
import com.springboot.sampleproject.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

//    @Async
    @Override
    public void saveBook(Map<String,Object> param) {

        bookMapper.saveBook(param);
        log.info("[AsyncMethod]" + "-" + param.get("idx").toString() + " " +  "Insert Data!");
   }

    @Override
    public void select(@SuppressWarnings("rawtypes") Map<String,Object> param, LargeDataRowHandler largeDataRowHandler) {
        largeDataRowHandler.startArray();
        bookMapper.selectLargeData(param,largeDataRowHandler);
        largeDataRowHandler.endArray();
        largeDataRowHandler.close();
    }

    @Override
    public Map<String,Object> selectLargeDataset(Map<String,Object> inParams, String datasetName) {
        SelectDataHandler rowHandler = new SelectDataRowHandler(inParams,datasetName);
        rowHandler.setParams(inParams);
        try{
            bookMapper.selectWithRowHandler(inParams, rowHandler);
        }  finally {
            rowHandler.close();
        }
        return inParams;
    }
}
