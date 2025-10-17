package hu.kovaribe.masterdata.masterdata.service;

import hu.kovaribe.masterdata.masterdata.entity.Person;
import jakarta.transaction.Transactional;

import java.util.List;

public interface PersonService {

    List<Person> findAll();

    Person findById(int id);

    Person save(Person person);

    @Transactional
    void deleteById(int id);
}
