package hu.kovaribe.masterdata.masterdata.dto;

import hu.kovaribe.masterdata.masterdata.enums.AddressStructure;

public record AddressDto(
        String country,
        String city,
        Integer zip,
        String street,
        Integer houseNumber,
        String statusCode,
        AddressStructure addressStructure
) {}
