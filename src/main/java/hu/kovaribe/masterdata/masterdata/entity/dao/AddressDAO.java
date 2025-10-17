package hu.kovaribe.masterdata.masterdata.entity.dao;

import hu.kovaribe.masterdata.masterdata.entity.Address;
import hu.kovaribe.masterdata.masterdata.enums.AddressStructure;

import java.util.List;

public interface AddressDAO {
    List<Address> findAll();

    List<Address> findByPersonId(int personId);

    Address findById(int id);

    Address save(Address address);

    void deleteById(int id);

    Address findByPersonIdAndStructure(int personId, AddressStructure structure);

    //boolean existsByPersonIdAndStructure(int personId, AddressStructure structure);
}
