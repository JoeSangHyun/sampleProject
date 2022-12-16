package com.springboot.sampleproject.model.dto;

import lombok.*;

@Getter
@Setter
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
