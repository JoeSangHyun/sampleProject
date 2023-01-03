package com.springboot.sampleproject.service;

import com.springboot.sampleproject.model.dto.Product;

import java.util.List;

// mybatis 테스트용
public interface TestService {
    public List<Product> getAllDataList();

    public Product getOneData(String idx);

}
