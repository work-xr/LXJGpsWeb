package com.sky.xljgps.msg;


/**
 * description:
 * author:  hefeng
 * create:  2018-07-11  下午1:35
 */

public class ResultMsg<T> {
    protected int code;
    protected String message;
    protected T data;

    public ResultMsg() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultMsg{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
