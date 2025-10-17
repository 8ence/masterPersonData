package hu.kovaribe.masterdata.masterdata.entity;

import hu.kovaribe.masterdata.masterdata.enums.ContactType;
import jakarta.persistence.*;

@Entity
@Table(name = "contact", uniqueConstraints =
        {@UniqueConstraint(name="uk.contact_address_type",
                columnNames = {"address_id", "contact_type"})
        })

public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "contact_type")
    private ContactType contactType;

    @Column(name = "contact_value")
    private String contactValue;

    @Column(name = "status_code")
    private String statusCode;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public Contact() {

    }

    public Contact(Address address, String statusCode, String contactValue, ContactType contactType) {
        this.address = address;
        this.statusCode = statusCode;
        this.contactValue = contactValue;
        this.contactType = contactType;
    }


    public int getId() {
        return id;
    }

    public void setId(int contactId) {
        this.id = contactId;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public String getContactValue() {
        return contactValue;
    }

    public void setContactValue(String contactValue) {
        this.contactValue = contactValue;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + id +
                ", contactType=" + contactType +
                ", contactValue='" + contactValue + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", address=" + address +
                '}';
    }

}