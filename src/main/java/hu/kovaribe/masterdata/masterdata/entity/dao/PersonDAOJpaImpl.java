package hu.kovaribe.masterdata.masterdata.entity.dao;

import hu.kovaribe.masterdata.masterdata.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDAOJpaImpl implements PersonDAO {

    private EntityManager entityManager;

    public PersonDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Person> findAll() {
        TypedQuery<Person> query = entityManager.createQuery("FROM Person", Person.class);
        List<Person> persons = query.getResultList();
        return persons;
    }

    @Override
    public Person findById(int id) {

        Person person = entityManager.find(Person.class, id);
        return person;
    }

    @Override
    public Person save(Person person) {

        Person dbPerson = entityManager.merge(person);
        return dbPerson;
    }

    @Override
    public void deleteById(int id) {

        Person person = entityManager.find(Person.class, id);
        entityManager.remove(person);
    }

}
