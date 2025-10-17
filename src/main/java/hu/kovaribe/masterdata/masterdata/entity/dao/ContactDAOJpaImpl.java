package hu.kovaribe.masterdata.masterdata.entity.dao;

import hu.kovaribe.masterdata.masterdata.entity.Contact;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactDAOJpaImpl implements ContactDAO {

    private EntityManager entityManager;

    public ContactDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Contact> findAll() {
        TypedQuery<Contact> query = entityManager.createQuery("FROM Contact", Contact.class);
        List<Contact> contacts = query.getResultList();
        return contacts;
    }

    @Override
    public List<Contact> findForAddressId(int addressId) {

        TypedQuery<Contact> query = entityManager.createQuery
                ( "SELECT a FROM Contact a WHERE a.address.id = :addressId" , Contact.class);
        query.setParameter("addressId", addressId);
        return query.getResultList();
    }

    @Override
    public Contact findById(int id) {

        Contact contact = entityManager.find(Contact.class, id);
        return contact;
    }

    @Override
    public Contact save(Contact contact) {

        Contact dbContact = entityManager.merge(contact);
        return dbContact;
    }

    @Override
    public void deleteById(int id) {

        Contact contact = entityManager.find(Contact.class, id);
        entityManager.remove(contact);
    }
}
