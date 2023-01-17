package com.springboot.sampleproject.util;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class LargeDataUtil {

//    private static final Logger log = LoggerFactory.getLogger(LargeDataUtil.class);

    private final HttpServletResponse response;
    private ServletOutputStream sos;

    public LargeDataUtil(Map<String,Object> inParams, HttpServletResponse response) {

        try {
            this.sos = response.getOutputStream();
        } catch (IOException e) {
            log.error("LargeDataUtils.writeResponseToJson.IOException : ", e);
//            throw new UserHandleException("MSG_COM_ERR_001");
        }
        this.response = response;
        inParams.put("SERVLET_OUTPUT_STREAM", this.sos);
    }
    /**
     * 파일-> Response Output 처리.
     */
    public void writeResponseToJson(HttpServletResponse response) {
        response.setHeader("Accept","*/*");
        response.setContentType("application/json;charset=UTF-8");

        try {
            this.sos.flush();
            this.response.flushBuffer();
        } catch(IOException e){
            log.error("LargeDataUtils.writeResponseToJson.IOException : ", e);
//            throw new Exception(e);
        } finally {
            if (this.sos != null) {
                try {
                    this.sos.close();
                } catch (IOException e) {
                    log.error("LargeDataUtils.IOException", e);
                }
            }
        }
    }
}
