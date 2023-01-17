package com.springboot.sampleproject.config.impl;

import com.google.gson.Gson;
import com.springboot.sampleproject.config.Constants;
import com.springboot.sampleproject.config.SelectDataHandler;
import jakarta.servlet.ServletOutputStream;
import org.apache.ibatis.session.ResultContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

// 대용량 처리용 #2
public class SelectDataRowHandler implements SelectDataHandler {
    private static final Logger log = LoggerFactory.getLogger(SelectDataRowHandler.class);
    private ServletOutputStream sos;

    private Map<String,Object> params;
    private Gson gson;
    private boolean isStarted = false;
    @Override
    public void setParams(Map<String,Object> params) {
        this.params = params;
    }

    public SelectDataRowHandler(Map<String,Object> inParams, String datasetName) {
        this.sos = (ServletOutputStream)inParams.get("SERVLET_OUTPUT_STREAM");
        this.gson = new Gson();

        String jsonStart = "{\"" + datasetName + "\":{ \"data\" : [";
        try {
            sos.write(jsonStart.getBytes(Constants.DEFAULT_CHARACTERSET));
        } catch (IOException e) {
            log.error("SelectDataRowHandler.Exception : ", e);
            try {
                sos.close();
            } catch (IOException ex) {
                log.error("SelectDataRowHandler.IOException", ex);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        String jsonEnd = "] } }";
        try {
            sos.write(jsonEnd.getBytes(Constants.DEFAULT_CHARACTERSET));
        } catch (IOException e) {
            log.error("SelectDataRowHandler.close.Exception : ", e);
            try {
                sos.close();
            } catch (IOException ex) {
                log.error("SelectDataRowHandler.close.IOException", ex);
            }
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getResultCnt() {
        return -1;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void handleResult(ResultContext resultContext) {
        Map<String,Object> dataMap = (Map<String,Object>) resultContext.getResultObject();

        try {
            if(isStarted){
                sos.write(", ".getBytes(Constants.DEFAULT_CHARACTERSET));
            } else{
                isStarted = true;
            }
            sos.write(gson.toJson(dataMap).getBytes(Constants.DEFAULT_CHARACTERSET));
        } catch (IOException e) {
            log.error("SelectDataRowHandler.handleResult.Exception : ", e);
            try {
                sos.close();
            } catch (IOException ex) {
                log.error("SelectDataRowHandler.handleResult.IOException", ex);
            }
            throw new RuntimeException(e);
        }
    }
}
