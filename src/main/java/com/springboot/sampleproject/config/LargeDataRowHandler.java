package com.springboot.sampleproject.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

// 대용량 처리 ResultHandler #1
public class LargeDataRowHandler implements ResultHandler<Map<String,Object>> {
    private final PrintWriter writer;
    private final JsonGenerator jsonGenerator;
    private final ObjectMapper mapper;

    public LargeDataRowHandler(PrintWriter writer){
        try {
            this.writer = writer;
            this.mapper = new ObjectMapper();
            this.jsonGenerator = mapper.getFactory().createGenerator(this.writer);
        } catch (IOException e) {
            throw new RuntimeException("JSON Streaming Exception", e);
        }
    }

    @Override
    public void handleResult(ResultContext<? extends Map<String, Object>> resultContext) {
        try{
            mapper.writeValue(jsonGenerator,resultContext.getResultObject());
        } catch (IOException e) {
            throw new RuntimeException("JSON Streaming Exception",e);
        }
    }

    public void startArray(){
        try{
            if(jsonGenerator != null){
                this.jsonGenerator.writeStartArray("Large Data : [");
            }
        } catch (IOException e){
            throw new RuntimeException("JSON Streaming Exception",e);
        }
    }

    public void endArray(){
        try {
            if(jsonGenerator != null){
                this.jsonGenerator.writeEndArray();
            }
        } catch (IOException e) {
            throw new RuntimeException("JSON streaming Exception", e);
        }
    }

    public void close(){
        try {
            if(jsonGenerator != null){
                this.jsonGenerator.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("JSON streaming Exception", e);
        }
    }
}
