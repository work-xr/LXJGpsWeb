package com.sky.xljgps.service;

import com.sky.xljgps.bean.Person;
import com.sky.xljgps.enums.ResultEnum;
import com.sky.xljgps.exception.PersonException;
import com.sky.xljgps.repository.GpsRepository;
import com.sky.xljgps.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public void insertTwo()
    {
        Person personA = new Person();
        personA.setName("sky");
        personA.setAge(18);
        personRepository.save(personA);

        Person personB = new Person();
        personB.setName("lotus");
        personB.setAge(17);
        personRepository.save(personB);
    }

    @Transactional
    public void getAge(Integer id)
    {
        Person person = personRepository.getOne(id);
        Integer age = person.getAge();

        logger.info("getAge, age={}", age);

        if (age < 10)
        {
            throw new PersonException(ResultEnum.PRIMARY_SCHOOL);
        }
        else if (age >=10 && age < 18)
        {
            throw new PersonException(ResultEnum.MIDDLE_SCHOOL);
        }

    }

    @Transactional
    public Person findOne(Integer id)
    {
        return personRepository.getOne(id);
    }

}
