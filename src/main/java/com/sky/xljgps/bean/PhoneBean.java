package com.sky.xljgps.bean;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class PhoneBean {
    @Id
    @GeneratedValue
    private Integer id;

    private String manufactory;
    private String model;
    private String imei;
    private String company;
    private String type;
    private String time;
    @Transient              // 该字段不写入数据库
    private String data;
    @Transient              // 该字段不写入数据库
    private String sign;

    private String sos_phone; // phone list
    private String name;

    public PhoneBean() {
    }

    @Override
    public String toString() {
        return "PhoneBean{" +
                "id=" + id +
                ", manufactory='" + manufactory + '\'' +
                ", model='" + model + '\'' +
                ", imei='" + imei + '\'' +
                ", company='" + company + '\'' +
                ", type='" + type + '\'' +
                ", time='" + time + '\'' +
                ", data='" + data + '\'' +
                ", sign='" + sign + '\'' +
                ", sos_phone='" + sos_phone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManufactory() {
        return manufactory;
    }

    public void setManufactory(String manufactory) {
        this.manufactory = manufactory;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

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

    public String getSosPhone() {
        return sos_phone;
    }

    public void setSosPhone(String sosPhone) {
        this.sos_phone = sosPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
