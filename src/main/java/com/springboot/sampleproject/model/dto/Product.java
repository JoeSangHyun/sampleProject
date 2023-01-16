package com.springboot.sampleproject.model.dto;

import lombok.*;

// Mybatis 테스트용 dto
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String idx;
    private String start_date;
    private String deadline;
    private String header;
    private String description;
    private String image_path;

}
