package com.springboot.sampleproject.model.dao;

import com.springboot.sampleproject.config.LargeDataRowHandler;
import com.springboot.sampleproject.model.dto.BookDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BookMapper {
    List<BookDto> getAllDataList();
    void saveBook(Map param);
    void selectLargeData(Map param, LargeDataRowHandler largeDataRowHandler);

    void selectData(Map<String,Object> param, LargeDataRowHandler largeDataRowHandler);
}
