package hu.kovaribe.masterdata.masterdata.mapper;

import hu.kovaribe.masterdata.masterdata.dto.ContactDto;
import hu.kovaribe.masterdata.masterdata.entity.Contact;

public class ContactMapper {
    public static ContactDto toDto(Contact c) {

        return new ContactDto(
                c.getId(),
                c.getContactType(),
                c.getContactValue(),
                c.getStatusCode());
        }
}
