package com.springboot.sampleproject.config;

// 변수 모음
public final class Constants {
    private Constants() {};

    public static final String DEFAULT_CHARACTERSET = "UTF-8";
    //CUD
    public static final String INSERT = "insert";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";

    //Paging
    public static final String START_ROW = "START_ROW";
    public static final String LIST_COUNT = "LIST_COUNT";
    public static final String TOTAL_COUNT = "totalCount";
    public static final String SKIP_COUNT = "SKIP_COUNT";

    //CODE
    public static final int ERROR_CODE_SUCCESS = 0;      	//처리 결과코드 - 정상
    public static final int ERROR_CODE_USEREXCEP = -1;   	//처리 결과코드 - UserHandleException
    public static final int ERROR_CODE_SYSEXCEP = -1000;	//처리 결과코드 - SystemException
    public static final int ERROR_CODE_NOAUTH = -10;		//처리 결과코드 - 해당 명령 실행 권한 없음
    public static final String ERROR_CODE = "ErrorCode";	//처리 결과코드 명
    public static final String ERROR_MESSAGE = "ErrorMsg";
    public static final String MESSAGE_CODE = "SVC_MSG_CD";
    public static final String MESSAGE_TEXT = "SVC_MSG_TEXT";
    public static final String ERROR_MESSAGE_CODE = "SVC_ERR_MSG_CD";
    public static final String ERROR_MESSAGE_TEXT = "SVC_ERR_MSG_TEXT";
    public static final String ERROR_DETAIL = "SVC_ERR_DETAIL";
    public static final String STATUS_MESSAGE_CODE = "SVC_STS_MSG_CD";
    public static final String STATUS_MESSAGE_TEXT = "SVC_STS_MSG_TEXT";
    public static final String BIND_MESSAGE = "SVC_BIND_MSG";
    public static final String CLIENT_IP = "remoteNetworkAddress";

    //일반적인 페이지의 레이아웃 접두사
    public static final String VIEW_PREFIX_CONTENTS = "base.contents.";
}
