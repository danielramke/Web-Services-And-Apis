package de.lyth.vehicleapi.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address {

    private String address;
    private String city;
    private String state;
    private String zip;

    public Address() { }

}
