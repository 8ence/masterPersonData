package hu.kovaribe.masterdata.masterdata.entity.dao;

import hu.kovaribe.masterdata.masterdata.entity.Person;

import java.util.List;

public interface PersonDAO {
    List<Person> findAll();

    Person findById(int id);

    Person save(Person person);

    void deleteById(int id);



}
