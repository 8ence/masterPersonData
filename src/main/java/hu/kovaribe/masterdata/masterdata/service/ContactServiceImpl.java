package hu.kovaribe.masterdata.masterdata.service;

import hu.kovaribe.masterdata.masterdata.dto.ContactDto;
import hu.kovaribe.masterdata.masterdata.entity.Address;
import hu.kovaribe.masterdata.masterdata.entity.Contact;
import hu.kovaribe.masterdata.masterdata.entity.dao.AddressDAO;
import hu.kovaribe.masterdata.masterdata.entity.dao.ContactDAO;
import hu.kovaribe.masterdata.masterdata.enums.ContactType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactDAO contactDAO;
    private final AddressDAO addressDAO;

    public ContactServiceImpl(ContactDAO contactDAO, AddressDAO addressDAO) {
        this.contactDAO = contactDAO;
        this.addressDAO = addressDAO;

    }

    @Override
    public List<Contact> findAll() {
        return contactDAO.findAll();
    }

    @Override
    public Contact findById(int id) {
        return contactDAO.findById(id);
    }

    @Override
    public List<Contact> findForAddress(int addressId) {
        return contactDAO.findForAddressId(addressId);
    }

    @Transactional
    @Override
    public Contact save(Contact contact) {
        ContactType contactType = contact.getContactType();
        if(contactType == null) {
            throw new IllegalArgumentException("Az elérhetőség típusát kötelező megadni");
        }

        return contactDAO.save(contact);
    }

    @Transactional
    @Override
    public Contact saveForAddress(int addressId, Contact contact) {
        Address address = addressDAO.findById(addressId);
        if (address == null) {
            throw new IllegalArgumentException("Nem létezik cím a megadott ID-val : " + addressId);
        }

        contact.setAddress(address);
        return contactDAO.save(contact);
    }

    @Transactional
    @Override
    public Contact updateForAddress(int addressId, int contactId, ContactDto dto) {
        Address address = addressDAO.findById(addressId);
        if (address == null) {
            throw new IllegalArgumentException("Nem található cím: " + addressId);
        }

        Contact existing = contactDAO.findById(contactId);
        if (existing == null) {
            throw new IllegalArgumentException("Nem található elérhetőség: " + contactId);
        }
        existing.setAddress(address);

        if (dto.contactType() != null) existing.setContactType(dto.contactType());
        if (dto.contactValue() != null) existing.setContactValue(dto.contactValue());

        return contactDAO.save(existing);
    }

    @Override
    public void deleteById(int id) {
        contactDAO.deleteById(id);
    }
}
