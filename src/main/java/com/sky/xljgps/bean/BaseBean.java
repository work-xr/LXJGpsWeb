package com.sky.xljgps.bean;

import javax.persistence.Embeddable;

//@Embeddable     // 如果是@Entity,需要主键,此地不要主键,但是该类要作为@Entity类的一部分
//错误提示:   Caused by: org.hibernate.AssertionFailure: Subclass has to be binded after it's mother class: com.sky.xljgps.bean.BaseBean
public class BaseBean {

    private String data;
    private String sign;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "data='" + data + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
