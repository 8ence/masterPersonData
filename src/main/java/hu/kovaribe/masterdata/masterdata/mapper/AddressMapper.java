package hu.kovaribe.masterdata.masterdata.mapper;

import hu.kovaribe.masterdata.masterdata.dto.AddressDto;
import hu.kovaribe.masterdata.masterdata.entity.Address;

public class AddressMapper {
    public static AddressDto toDto(Address a) {

        return new AddressDto(
                a.getCountry(),
                a.getCity(),
                a.getZip(),
                a.getStreet(),
                a.getHouseNumber(),
                a.getStatusCode(),
                a.getAddressStructure()
        );
    }
}
