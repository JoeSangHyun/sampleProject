package com.springboot.sampleproject.controller;

import com.springboot.sampleproject.model.dto.Product;
import com.springboot.sampleproject.service.impl.TestServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static  org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static  org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static  org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TestController.class)
public class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TestServiceImpl testServiceImpl;

    @Test
    @DisplayName("MockMvc를 통한 데이터 가져오기 테스트")
    void getPortfoiloTest() throws Exception {
        given(testServiceImpl.getOneData("1")).willReturn(
                new Product("1","20221223","99","SUAPAPA","마이바티스 테스트","c:\\path"));

//        String productId = "1";

        mockMvc.perform(
                get("/testOne?idx=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idx").exists())
                .andExpect(jsonPath("$.start_date").exists())
                .andExpect(jsonPath("$.deadline").exists())
                .andExpect(jsonPath("$.header").exists())
                .andExpect(jsonPath("$.description").exists())
                .andExpect(jsonPath("$.image_path").exists())
                .andDo(print());

        verify(testServiceImpl).getOneData("1");

    }
}
