package hu.kovaribe.masterdata.masterdata.service;

import hu.kovaribe.masterdata.masterdata.entity.Person;
import hu.kovaribe.masterdata.masterdata.entity.dao.PersonDAO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonDAO personDAO;

    public PersonServiceImpl(PersonDAO thePersonDAO) {
        this.personDAO = thePersonDAO;
    }

    @Override
    public List<Person> findAll() {
        return personDAO.findAll();
    }

    @Override
    public Person findById(int id) {
        return personDAO.findById(id);
    }

    @Transactional
    @Override
    public Person save(Person person) {
        return personDAO.save(person);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        personDAO.deleteById(id);
    }
}
