package com.sky.xljgps.controller;

import com.sky.xljgps.bean.Person;
import com.sky.xljgps.msg.ResultMsg;
import com.sky.xljgps.repository.PersonRepository;
import com.sky.xljgps.service.PersonService;
import com.sky.xljgps.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/person")
    public List<Person> getPersons()
    {
        logger.info("getPersons............");
        return personRepository.findAll();
    }

    @PostMapping(value = "/person")
    public ResultMsg personAdd(@Valid Person person, BindingResult result)
    {
        if (result.hasErrors())
        {
            return ResultUtil.error(1, result.getFieldError().getDefaultMessage());
        }
        person.setName(person.getName());
        person.setAge(person.getAge());
        logger.info("personAdd............");

        return ResultUtil.success(personRepository.save(person));
    }

    @GetMapping(value = "/person/{id}")
    public Optional<Person> personFindOne(@PathVariable("id") Integer id)
    {
        logger.info("personFindOne............");
        return personRepository.findById(id);
    }

    @PutMapping(value = "/person/{id}")
    public Person personUpdate(@PathVariable("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("age") Integer age)
    {
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setAge(age);
        logger.info("personUpdate............");

        return personRepository.save(person);
    }

    @DeleteMapping(value = "/person/{id}")
    public void personDeleteOne(@PathVariable("id") Integer id)
    {
        logger.info("personDeleteOne............");
        personRepository.deleteById(id);
    }

    @GetMapping(value = "/person/age/{age}")
    public List<Person> personFindByAge(@PathVariable("age") Integer age)
    {
        logger.info("personFindByAge............");
        return personRepository.findByAge(age);
    }

    @PostMapping(value = "/person/two")
    public void personAddTwo()
    {
        logger.info("personAddTwo............");
        personService.insertTwo();
    }

    @GetMapping(value = "person/getAge/{id}")
    public void personGetAge(@PathVariable("id") Integer id)
    {
        personService.getAge(id);
    }
}
