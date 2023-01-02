package com.springboot.sampleproject.service.impl;

import com.springboot.sampleproject.model.dao.TestMapper;
import com.springboot.sampleproject.model.dto.Product;
import com.springboot.sampleproject.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final TestMapper testMapper;


    @Override
    public List<Product> getAllDataList() {
        return testMapper.getAllDataList();
    }

    @Override
    public Product getOneData(String idx) { return testMapper.getOneData(idx); }



}
