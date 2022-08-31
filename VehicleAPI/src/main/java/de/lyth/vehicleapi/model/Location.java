package de.lyth.vehicleapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Embeddable
public class Location {

    @NotNull
    private Double lat;

    @NotNull
    private Double lon;

    @Transient
    private String address;

    @Transient
    private String city;

    @Transient
    private String state;

    @Transient
    private String zip;

    public Location() { }

    public Location(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

}
