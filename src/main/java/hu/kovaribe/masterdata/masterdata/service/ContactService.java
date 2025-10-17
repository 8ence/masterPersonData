package hu.kovaribe.masterdata.masterdata.service;

import hu.kovaribe.masterdata.masterdata.dto.ContactDto;
import hu.kovaribe.masterdata.masterdata.entity.Contact;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();

    Contact findById(int id);

    List<Contact> findForAddress(int addressId);

    Contact save(Contact contact);

    Contact saveForAddress(int addressId, Contact contact);

    Contact updateForAddress(int addressId, int contactId, ContactDto dto);

    @Transactional
    void deleteById(int id);
}
