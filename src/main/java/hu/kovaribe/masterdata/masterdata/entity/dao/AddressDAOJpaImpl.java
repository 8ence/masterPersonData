package hu.kovaribe.masterdata.masterdata.entity.dao;

import hu.kovaribe.masterdata.masterdata.entity.Address;
import hu.kovaribe.masterdata.masterdata.enums.AddressStructure;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDAOJpaImpl implements AddressDAO {

    private EntityManager entityManager;

    public AddressDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Address> findAll() {

        TypedQuery<Address> query = entityManager.createQuery("FROM Address", Address.class);
        List<Address> addresses = query.getResultList();
        return addresses;
    }

    @Override
    public List<Address> findByPersonId(int personId) {

        TypedQuery<Address> query = entityManager.createQuery
                ( "SELECT a FROM Address a WHERE a.person.id = :personId" , Address.class);
        query.setParameter("personId", personId);
        return query.getResultList();
    }

    @Override
    public Address findById(int id) {

        Address address = entityManager.find(Address.class, id);
        return address;
    }

    @Override
    public Address save(Address address) {

        Address dbAddress = entityManager.merge(address);
        return dbAddress;
    }

    @Override
    public void deleteById(int id) {

        Address address = entityManager.find(Address.class, id);
        entityManager.remove(address);
    }

    @Override
    public Address findByPersonIdAndStructure(int personId, AddressStructure structure) {

        TypedQuery<Address> query = entityManager.createQuery
                ("SELECT a FROM Address a WHERE a.person.id=:pid AND a.addressStructure=:st", Address.class);
        query.setParameter("pid", personId);
        query.setParameter("st", structure);
        return query.getResultStream().findFirst().orElse(null);
    }

    /*@Override
    public boolean existsByPersonIdAndStructure(int personId, AddressStructure structure) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(a) FROM Address a WHERE a.person.id = :pid AND a.addressStructure = :st",
                Long.class
        );
        query.setParameter("pid", personId);
        query.setParameter("st", structure);
        return query.getSingleResult() > 0;
    }*/
}
