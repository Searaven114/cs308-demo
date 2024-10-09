package com.borau.cs308demo.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "addresses")
public class Address {

    public Address(String street, String city, String zipCode, String country) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Address(String street, String city, String country, String zipCode, String notes) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.notes = notes;
    }

    @Id
    private String id;
    private String street;
    private String city;
    private String zipCode;
    private String country;
    private String notes;


}
