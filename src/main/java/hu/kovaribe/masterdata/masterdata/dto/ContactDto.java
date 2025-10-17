package hu.kovaribe.masterdata.masterdata.dto;

import hu.kovaribe.masterdata.masterdata.enums.ContactType;

public record ContactDto(
        //ContactType contactType,
        //String contactValue
        Integer id,
        ContactType contactType,
        String contactValue,
        String statusCode
) {}
