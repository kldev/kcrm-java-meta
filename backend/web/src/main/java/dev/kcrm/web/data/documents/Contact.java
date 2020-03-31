package dev.kcrm.web.data.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "crm.contact")
public class Contact {

    @Id
    private String id;

    private String name;

    private String lastName;

    private List<String> emails = new ArrayList<>();

    private List<String> phones = new ArrayList<>();

    private String primaryEmail;

    private String primaryPhone;

    private String city;

    private String country;

    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public static final class ContactBuilder {
        private String id;
        private String name;
        private String lastName;
        private List<String> emails = new ArrayList<>();
        private List<String> phones = new ArrayList<>();
        private String primaryEmail;
        private String primaryPhone;
        private String city;
        private String country;
        private String address;

        private ContactBuilder() {
        }

        public static ContactBuilder aContact() {
            return new ContactBuilder();
        }

        public ContactBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public ContactBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ContactBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ContactBuilder withEmails(List<String> emails) {
            this.emails = emails;
            return this;
        }

        public ContactBuilder withEmail(String email) {

            if (this.emails.contains(email) == false) {
                this.emails.add(email);
            }

            return this;
        }


        public ContactBuilder withPhones(List<String> phones) {
            this.phones = phones;
            return this;
        }

        public ContactBuilder withPhone(String phone) {

            if (this.phones.contains(phone) == false) {
                this.phones.add(phone);
            }

            return this;
        }

        public ContactBuilder withPrimaryEmail(String primaryEmail) {
            this.primaryEmail = primaryEmail;
            return this;
        }

        public ContactBuilder withPrimaryPhone(String primaryPhone) {
            this.primaryPhone = primaryPhone;
            return this;
        }

        public ContactBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public ContactBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public ContactBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Contact build() {
            Contact contact = new Contact();
            contact.setId(id);
            contact.setName(name);
            contact.setLastName(lastName);
            contact.setEmails(emails);
            contact.setPhones(phones);
            contact.setPrimaryEmail(primaryEmail);
            contact.setPrimaryPhone(primaryPhone);
            contact.setCity(city);
            contact.setCountry(country);
            contact.setAddress(address);
            return contact;
        }
    }
}
