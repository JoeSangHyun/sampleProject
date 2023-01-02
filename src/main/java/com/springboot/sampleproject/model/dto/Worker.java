package com.springboot.sampleproject.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Worker {
    private String id;
    private double x;
    private double y;
    private double z;

    private int poseName;
    private String riskLevel;

    @Override
    public String toString() {
       return "{" +
                "id:" +  id + "," +
                "currentPosition: {"+
                "x:" + x + "," +
                "y:" + y + "," +
                "z:" + z + "," +
                "poseName:" + poseName + "," +
                "riskLevel:" +  riskLevel +
                "}" +
            "}";
    }
}
