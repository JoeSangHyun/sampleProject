package com.springboot.sampleproject.config;

import org.apache.ibatis.session.ResultHandler;

import java.util.Map;

/**
 * 대량 데이터를 처리하기 위한 인터페이스<br>
 * 인터페이스를 상속받아서 다양한 채널 통합시에 활용<br>
 *
 * 대용량 처리 #2
 */
@SuppressWarnings("rawtypes")
public interface SelectDataHandler extends ResultHandler {

    /**
     * Parameters 를 세팅
     */
    public void setParams(Map<String,Object> params);

    /**
     * 데이터를 모두처리하고 자원해제하는 메소드
     */
    public void close();

    /**
     * resultContext.getResultCount() 조회
     */
    public int getResultCnt();
}
