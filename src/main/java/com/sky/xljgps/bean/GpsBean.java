package com.sky.xljgps.bean;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GpsBean {//extends BaseBean{

    @Id
    @GeneratedValue
    private Integer id;

    private String position_type;
    private String loc_type;
    private String longitude;
    private String latitude;
    private String power;

    public GpsBean() {
    }

    @Override
    public String toString() {
        return "GpsBean{" +
                "id=" + id +
                ", position_type='" + position_type + '\'' +
                ", loc_type='" + loc_type + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", power='" + power + '\'' +
                ", manufactory='" + manufactory + '\'' +
                ", model='" + model + '\'' +
                ", imei='" + imei + '\'' +
                ", company='" + company + '\'' +
                ", type='" + type + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition_type() {
        return position_type;
    }

    public void setPosition_type(String position_type) {
        this.position_type = position_type;
    }

    public String getLoc_type() {
        return loc_type;
    }

    public void setLoc_type(String loc_type) {
        this.loc_type = loc_type;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    private String manufactory;
    private String model;
    private String imei;
    private String company;
    private String type;
    private String time;

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
}
