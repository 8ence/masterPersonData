package hu.kovaribe.masterdata.masterdata.service;

import hu.kovaribe.masterdata.masterdata.dto.AddressDto;
import hu.kovaribe.masterdata.masterdata.entity.Address;
import jakarta.transaction.Transactional;

import java.util.List;

public interface AddressService {
    List<Address> findAll();

    Address findById(int id);

    List<Address> findForPerson(int personId);

    Address save(Address address);

    @Transactional
    Address saveForPerson(int personId, AddressDto dto);

    Address updateForPerson(int personId, int addressId, AddressDto dto);

    @Transactional
    void deleteById(int id);

    List<AddressDto> findAllDtos();

    AddressDto findByIdDto(int id);

    List<AddressDto> findForPersonDto(int personId);

}
