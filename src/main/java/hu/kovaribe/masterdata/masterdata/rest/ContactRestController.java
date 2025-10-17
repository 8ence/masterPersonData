package hu.kovaribe.masterdata.masterdata.rest;

import hu.kovaribe.masterdata.masterdata.dto.ContactDto;
import hu.kovaribe.masterdata.masterdata.entity.Contact;
import hu.kovaribe.masterdata.masterdata.service.ContactServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactRestController {

    private ContactServiceImpl contactService;

    public ContactRestController(ContactServiceImpl contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/find/all")
    public List<ContactDto> getAllContacts() {
        return contactService.findAllDtos();
    }

    @GetMapping("findById/{id}")
    public ContactDto getContactById(@PathVariable int id) {
        return contactService.findByIdDto(id);
    }

    @GetMapping("/findForAddress/{addressId}")
    public List<ContactDto> getContactByAddressId(@PathVariable int addressId) {
        return contactService.findForAddressDto(addressId);
    }

    @PostMapping("/create/{addressId}")
    public Contact createForAddress(@PathVariable int addressId, @RequestBody Contact contact) {
        contact.setId(0);
        return contactService.saveForAddress(addressId, contact);
    }

    @PutMapping("/update/{addressId}/{contactId}")
    public ResponseEntity<Contact> updateContact(
            @PathVariable int addressId,
            @PathVariable int contactId,
            @RequestBody ContactDto dto) {

        Contact updated = contactService.updateForAddress(addressId, contactId, dto);
        return ResponseEntity.ok(updated);

    }

    @DeleteMapping("/delete/{contactId}")
    public String deleteContact(@PathVariable int contactId) {
        Contact contact = contactService.findById(contactId);
        if(contact == null) {
            throw new RuntimeException("Nem található elérhetőség az adott azonosítóval : " + contactId);
        }
        contactService.deleteById(contactId);
        return "Az elérhetőség sikeresen törölve : " + contactId;
    }

}
