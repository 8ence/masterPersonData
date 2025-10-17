package hu.kovaribe.masterdata.masterdata.entity.dao;

import hu.kovaribe.masterdata.masterdata.dto.ContactDto;
import hu.kovaribe.masterdata.masterdata.entity.Contact;

import java.util.List;

public interface ContactDAO {

    List<Contact> findAll();

    List<Contact> findForAddressId(int addressId);

    Contact findById(int id);

    Contact save(Contact contact);

    List<ContactDto> findAllDtos();

    void deleteById(int id);
}
