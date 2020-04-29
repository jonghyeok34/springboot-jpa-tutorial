package com.example.demo.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.example.demo.dao.PersonJpaDao;
import com.example.demo.model.jpa.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v2/person")
@RestController
public class PersonJpaController {

    private final PersonJpaDao personDao;

    @Autowired
    public PersonJpaController(PersonJpaDao personDao) {
        this.personDao = personDao;
    }

    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody Person person) {
        
        person.setId(personDao.generateRandomId());
        personDao.save(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personDao.findAll();
    }

    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return personDao.findById(id).get();
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id) {
        personDao.deleteById(id);
    }

    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person personToUpdate) {
        personDao.findById(id).map(person -> {
            System.out.println('1');
            person.setId(id);
            person.setName(personToUpdate.getName());
            return personDao.save(person);
        }).orElseGet(() -> {
            
            System.out.println('2');
            personToUpdate.setId(id);
            return personDao.save(personToUpdate);
        });
        ;
    }
}