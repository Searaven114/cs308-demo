package com.borau.cs308demo.distributor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Document(collection = "distributors")
public class Distributor {

    @Id
    private String id;
    private String name;
    private String address;
    private String phone;
    private String website;
    private boolean isActive; // Operational/non operational : distributor listelerken ....isActiveTrue ekle sonuna, admin paneline de distributor kontrolü verilir.

    public Distributor(String name, String address, String phone, String website, boolean isActive) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.isActive = isActive;
    }
}