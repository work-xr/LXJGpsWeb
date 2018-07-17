package com.sky.xljgps.msg;

import java.util.List;

/**
 * description:
 * author:  hefeng
 * create:  2018-07-13  下午4:43
 */

public class PhoneMsg {
    private String relationship;
    private String phone;

    public PhoneMsg() {
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PhoneMsg{" +
                "relationship='" + relationship + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
