package hu.kovaribe.masterdata.masterdata.service;

import hu.kovaribe.masterdata.masterdata.dto.AddressDto;
import hu.kovaribe.masterdata.masterdata.entity.Address;
import hu.kovaribe.masterdata.masterdata.entity.Person;
import hu.kovaribe.masterdata.masterdata.entity.dao.AddressDAO;
import hu.kovaribe.masterdata.masterdata.entity.dao.PersonDAO;
import hu.kovaribe.masterdata.masterdata.enums.AddressStructure;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressDAO addressDAO;
    private final PersonDAO personDAO;
    public AddressDto addressDto;

    public AddressServiceImpl(AddressDAO addressDAO, PersonDAO personDAO, EntityManager entityManager) {
        this.addressDAO = addressDAO;
        this.personDAO = personDAO;
    }


    @Override
    public List<Address> findAll() {
        return addressDAO.findAll();
    }

    @Override
    public Address findById(int id) {
        return addressDAO.findById(id);
    }

    @Override
    public List<Address> findForPerson(int personId) {
        return addressDAO.findByPersonId(personId);
    }

    @Transactional
    @Override
    public Address save(Address address) {
        AddressStructure structure = address.getAddressStructure();
        if(structure == null) {
            throw new IllegalArgumentException("A címstípust kötelező megadni");
        }

        return addressDAO.save(address);
    }

    @Transactional
    @Override
    public Address saveForPerson(int personId, AddressDto dto) {
        System.out.println("### RAW DTO: " + dto);
        System.out.println("### DTO structure: " + dto.addressStructure());
        Person person = personDAO.findById(personId);

        if (person == null) {
            throw new IllegalArgumentException("Nem létezik személy a megadott ID-val : " + personId);
        }

        AddressStructure structure = dto.addressStructure();
        if (structure == null) {
            throw new IllegalArgumentException("A cím típusát (addressStructure) kötelező megadni.");
        }

        Address existingSameType = addressDAO.findByPersonIdAndStructure(personId, structure);
        if (existingSameType != null) {
            throw new RuntimeException("Már létezik a megadott címtípus: " + structure.getLabel());
        }

        Address newAddress = new Address();
        newAddress.setPerson(person);
        newAddress.setCountry(dto.country());
        newAddress.setCity(dto.city());
        newAddress.setZip(dto.zip());
        newAddress.setStreet(dto.street());
        newAddress.setHouseNumber(dto.houseNumber());
        newAddress.setStatusCode(dto.statusCode());
        newAddress.setAddressStructure(dto.addressStructure());

        System.out.println("### ENTITY structure after set: " + newAddress.getAddressStructure());
        return upsertWithUniqueness(personId, newAddress);
    }

    @Transactional
    @Override
    public Address updateForPerson(int personId, int addressId, AddressDto dto) {
        Person person = personDAO.findById(personId);
        if (person == null) {
            throw new IllegalArgumentException("Magánszemély nem található: " + personId);
        }

        Address address = addressDAO.findById(addressId);
        if (address == null) {
            throw new IllegalArgumentException("A cím nem található: " + addressId);
        }
        address.setPerson(person);

        address.setCountry(dto.country());
        address.setCity(dto.city());
        address.setZip(dto.zip());
        address.setStreet(dto.street());
        address.setHouseNumber(dto.houseNumber());
        address.setStatusCode(dto.statusCode());
        address.setAddressStructure(dto.addressStructure());

        return upsertWithUniqueness(personId, address);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        addressDAO.deleteById(id);
    }

    @Override
    public List<AddressDto> findAllDtos() {
        return addressDAO.findAll().stream()
                .map(address -> new AddressDto(
                        address.getCountry(),
                        address.getCity(),
                        address.getZip(),
                        address.getStreet(),
                        address.getHouseNumber(),
                        address.getStatusCode(),
                        address.getAddressStructure()
                ))
                .toList();
    }

    @Override
    public AddressDto findByIdDto(int id) {
        Address address = addressDAO.findById(id);
        return new AddressDto(
                address.getCountry(),
                address.getCity(),
                address.getZip(),
                address.getStreet(),
                address.getHouseNumber(),
                address.getStatusCode(),
                address.getAddressStructure()
        );
    }

    @Override
    public List<AddressDto> findForPersonDto(int personId) {
        return addressDAO.findByPersonId(personId).stream()
                .map(address -> new AddressDto(
                        address.getCountry(),
                        address.getCity(),
                        address.getZip(),
                        address.getStreet(),
                        address.getHouseNumber(),
                        address.getStatusCode(),
                        address.getAddressStructure()
                ))
                .toList();
    }

    @Transactional
    private Address upsertWithUniqueness(int personId, Address address) {
        AddressStructure structure = address.getAddressStructure();

        Address existingSameType = addressDAO.findByPersonIdAndStructure(personId, structure);

        if (structure == null) {
            throw new IllegalArgumentException("Címtípus megadása kötelező!");
        }

        if (address.getId() == 0) {
            //CREATE
            if (existingSameType != null) {
                throw new RuntimeException("Már létezik a megadott címtípus: " + structure.getLabel());
            }
        } else {
            //UPDATE
            if (existingSameType != null && existingSameType.getId() != address.getId()) {
                throw new RuntimeException("Már létezik a megadott címtípus: " + structure.getLabel());
            }
        }

        return addressDAO.save(address);
    }

}
