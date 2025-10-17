package hu.kovaribe.masterdata.masterdata.rest;

import hu.kovaribe.masterdata.masterdata.dto.AddressDto;
import hu.kovaribe.masterdata.masterdata.entity.Address;
import hu.kovaribe.masterdata.masterdata.service.AddressServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressRestController {

    private AddressServiceImpl addressService;

    public AddressRestController(AddressServiceImpl addressServiceImpl) {
        this.addressService = addressServiceImpl;

    }

    @GetMapping("/findAll")
    public List<Address> getAllAddress() {
        return addressService.findAll();
    }

    @GetMapping("/find/{personId}")
    public List<Address> getAddresses(@PathVariable int personId) {
        return addressService.findForPerson(personId);
    }

    @GetMapping("/findById/{addressId}")
    public Address getAddress(@PathVariable int addressId) {
        return addressService.findById(addressId);
    }

    @PostMapping("/create/{personId}")
    public ResponseEntity<Address> createAddress(@PathVariable int personId, @RequestBody AddressDto dto) {

        Address created = addressService.saveForPerson(personId, dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update/{personId}/{addressId}")
    public ResponseEntity<Address> updateAddress(
            @PathVariable int personId,
            @PathVariable int addressId,
            @RequestBody AddressDto dto) {

        Address updated = addressService.updateForPerson(personId, addressId, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{addressId}")
    public String deletePerson(@PathVariable int addressId) {

        Address address = addressService.findById(addressId);
        if(address == null) {
            throw new RuntimeException("Nem található cím az adott azonosítóval : " + addressId);
        }
        addressService.deleteById(addressId);
        return "A cím sikeresen törölve : " + addressId;
    }
}
