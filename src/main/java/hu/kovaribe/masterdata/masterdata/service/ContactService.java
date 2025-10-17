package hu.kovaribe.masterdata.masterdata.service;

import hu.kovaribe.masterdata.masterdata.dto.ContactDto;
import hu.kovaribe.masterdata.masterdata.entity.Contact;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ContactService {

    List<Contact> findForAddress(int addressId);

    Contact findById(int contactId);

    Contact save(Contact contact);

    Contact saveForAddress(int addressId, Contact contact);

    Contact updateForAddress(int addressId, int contactId, ContactDto dto);

    List<ContactDto> findAllDtos();

    ContactDto findByIdDto(int id);

    List<ContactDto> findForAddressDto(int addressId);

    @Transactional
    void deleteById(int id);
}
