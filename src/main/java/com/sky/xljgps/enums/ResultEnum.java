package com.sky.xljgps.enums;

public enum  ResultEnum {
    UNKNOW_ERROR(-1, "unknow error"),
    SUCCESS(0, "success"),
    PRIMARY_SCHOOL(100, "go to primary school"),
    MIDDLE_SCHOOL(101, "go to middle school"),
    VERTIFICATION_ERROR(100, "vertification sign error"),
    PARAM_ERROR(101, "parse params error"),
    PARAM_EMPTY(102, "parse empty error"),
    DECODE_ERROR(103, "decode error"),

    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
