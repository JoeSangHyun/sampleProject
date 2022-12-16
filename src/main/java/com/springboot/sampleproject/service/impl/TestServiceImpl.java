package com.springboot.sampleproject.service.impl;

import com.springboot.sampleproject.model.dao.TestMapper;
import com.springboot.sampleproject.model.dto.Product;
import com.springboot.sampleproject.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final TestMapper testMapper;

    @Override
    public List<Product> getAllDataList() {
        return testMapper.getAllDataList();
    }
}
