package com.springboot.sampleproject.service.impl;

import com.springboot.sampleproject.config.LargeDataRowHandler;
import com.springboot.sampleproject.model.dao.TestMapper;
import com.springboot.sampleproject.model.dto.Product;
import com.springboot.sampleproject.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

// mybatis 테스트용
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

    @Override
    public void select(@SuppressWarnings("rawtypes") Map param, LargeDataRowHandler largeDataRowHandler)
    {
        largeDataRowHandler.startArray();
        testMapper.selectLargeData(param,largeDataRowHandler);
        largeDataRowHandler.endArray();
        largeDataRowHandler.close();
    }
}
