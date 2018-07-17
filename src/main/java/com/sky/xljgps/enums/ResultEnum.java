package com.sky.xljgps.enums;

/**
 * description: 自定义所有的错误返回值和信息
 * author:  hefeng
 * create:  2018-07-11  下午1:35
 */

public enum  ResultEnum {
    UNKNOW_ERROR(-1, "unknow error"),
    SUCCESS(0, "success"),
    PRIMARY_SCHOOL(100, "go to primary school"),
    MIDDLE_SCHOOL(101, "go to middle school"),
    VERTIFICATION_ERROR(10, "vertification sign error"),
    PARAM_ERROR(11, "parse params error"),
    PARAM_EMPTY(12, "parse empty error"),
    DECODE_ERROR(13, "decode error"),

    PHONEINFO_GET_LATEST(20, "get latest phoneinfo error, it's empty"),
    PHONEINFO_FIND_ERROR(21, "get phoneinfo by id error"),

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
