package com.springboot.sampleproject.model.dao;

import com.springboot.sampleproject.config.LargeDataRowHandler;
import com.springboot.sampleproject.model.dto.BookDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BookMapper {
    List<BookDto> getAllDataList();
    void saveBook(Map<String,Object> param);
    void selectLargeData(Map<String,Object> param, LargeDataRowHandler largeDataRowHandler);

    void selectData(Map<String,Object> param, LargeDataRowHandler largeDataRowHandler);

    void selectWithRowHandler(Map<String,Object> inParam, ResultHandler<?> resultHandler);
}
