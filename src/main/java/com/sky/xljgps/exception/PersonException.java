package com.sky.xljgps.exception;

import com.sky.xljgps.enums.ResultEnum;

public class PersonException extends RuntimeException {
    private Integer code;

    public PersonException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
