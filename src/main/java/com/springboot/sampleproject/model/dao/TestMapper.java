package com.springboot.sampleproject.model.dao;

import com.springboot.sampleproject.model.dto.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

// Mybatis 테스트용 Mapper
@Repository
@Mapper
public interface TestMapper {
    List<Product> getAllDataList();

    Product getOneData(String idx);
}
