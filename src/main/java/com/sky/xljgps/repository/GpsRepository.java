package com.sky.xljgps.repository;

import com.sky.xljgps.bean.GpsBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GpsRepository extends JpaRepository<GpsBean, Integer> {
}
