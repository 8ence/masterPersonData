package hu.kovaribe.masterdata.masterdata.entity;

import hu.kovaribe.masterdata.masterdata.enums.AddressStructure;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "address", uniqueConstraints =
        {@UniqueConstraint(name="uk.address_person_structure",
                columnNames = {"person_id", "address_structure"})
        })

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_structure")
    private AddressStructure addressStructure;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "zip")
    private int zip;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private int houseNumber;

    @Column(name = "status_code")
    private String statusCode;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(
            mappedBy = "address",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Contact> contacts = new ArrayList<>();

    public Address(){

    }

    public Address(Person person, String statusCode, int houseNumber, String street, int zip, String city, String country, AddressStructure addressStructure) {
        this.person = person;
        this.statusCode = statusCode;
        this.houseNumber = houseNumber;
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.country = country;
        this.addressStructure = addressStructure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public AddressStructure getAddressType() {
        return addressStructure;
    }

    public void setAddressType(AddressStructure addressStructure) {
        this.addressStructure = addressStructure;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public AddressStructure getAddressStructure() {
        return this.addressStructure;
    }

    public void setAddressStructure(AddressStructure addressStructure) {
        this.addressStructure = addressStructure;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", zip=" + zip +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", statusCode='" + statusCode + '\'' +
                ", person=" + person +
                '}';
    }
}
