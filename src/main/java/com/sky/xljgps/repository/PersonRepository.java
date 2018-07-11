package com.sky.xljgps.repository;

import com.sky.xljgps.bean.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Component
//@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    public List<Person> findByAge(Integer age);
}
