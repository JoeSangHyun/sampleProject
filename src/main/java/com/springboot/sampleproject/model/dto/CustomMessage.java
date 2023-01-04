package com.springboot.sampleproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomMessage {
    private String text;
    private int priority;
    private boolean secret;

    @Override
    public String toString(){
        return "CustomMessage {" +
                "text='" + text + '\'' +
                ", priority=" + priority +
                ", secret=" + secret +
                '}';
    }

}
