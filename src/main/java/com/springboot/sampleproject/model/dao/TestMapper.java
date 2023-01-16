package com.springboot.sampleproject.model.dao;

import com.springboot.sampleproject.config.LargeDataRowHandler;
import com.springboot.sampleproject.model.dto.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

// Mybatis 테스트용 Mapper
@Repository
@Mapper
public interface TestMapper {
    List<Product> getAllDataList();

    Product getOneData(String idx);
    // 대용량 처리는 반드시 리턴타입을 지정하지 말아야 한다. 지정하면 그것이 우선순위가 되어
    // handler로 가지 않는다.

    void selectLargeData(Map param,LargeDataRowHandler largeDataRowHandler);
}
