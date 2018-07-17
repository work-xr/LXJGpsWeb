package com.sky.xljgps.repository;

import com.sky.xljgps.bean.PhoneBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<PhoneBean, Integer> {
    //PhoneBean findPhoneInfoById(Integer id);          // useless
    //PhoneBean findPhoneInfoByIMEI(String imei);
}
