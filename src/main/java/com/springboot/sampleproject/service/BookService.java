package com.springboot.sampleproject.service;

import com.springboot.sampleproject.config.LargeDataRowHandler;
import com.springboot.sampleproject.model.dto.BookDto;

import java.util.List;
import java.util.Map;

public interface BookService {

    public List<BookDto> getAllDataList();
    public void selectData(Map<String,Object> param, LargeDataRowHandler largeDataRowHandler);
    public void saveBook(int i);
    public void select(@SuppressWarnings("rawtypes") Map param, LargeDataRowHandler largeDataRowHandler);
}
