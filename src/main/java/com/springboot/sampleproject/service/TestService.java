package com.springboot.sampleproject.service;

import com.springboot.sampleproject.config.LargeDataRowHandler;
import com.springboot.sampleproject.model.dto.Product;

import java.util.List;
import java.util.Map;

// mybatis 테스트용
public interface TestService {
    public List<Product> getAllDataList();

    public Product getOneData(String idx);

    public void select(@SuppressWarnings("rawtypes") Map param, LargeDataRowHandler largeDataRowHandler);

}
