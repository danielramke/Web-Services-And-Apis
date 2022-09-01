package de.lyth.vehicleapi.model;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is used to store address information like city, state and zipcode.
 */
@Setter
@Getter
public class Address {

    private String address;
    private String city;
    private String state;
    private String zip;

    public Address() { }

}
