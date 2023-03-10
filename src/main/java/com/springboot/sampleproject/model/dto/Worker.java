package com.springboot.sampleproject.model.dto;

import lombok.*;

// 작업자 정보 dto
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Worker {
    private String id;
    private double x;
    private double y;
    private double z;

    private int poseName = 1;
    private String riskLevel;

    @Override
    public String toString() {
       return "{" +
                "id:" +  id + "," +
                "currentPosition:" + " {"+
                    "x:" + x + "," +
                    "y:" + y + "," +
                    "z:" + z + "," +
                    "poseName:" + poseName + "," +
                    "riskLevel:" +  riskLevel +
                    "}" +
            "}";
    }
}
