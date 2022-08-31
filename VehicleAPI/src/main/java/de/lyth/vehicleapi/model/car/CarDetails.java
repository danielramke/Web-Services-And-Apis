package de.lyth.vehicleapi.model.car;

import de.lyth.vehicleapi.model.Manufacturer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Embeddable
public class CarDetails {

    @NotBlank
    private String body;

    @NotBlank
    private String model;

    @NotNull
    @ManyToOne
    private Manufacturer manufacturer;

    private Integer doors;
    private String typeOfFuel;
    private String engine;
    private Integer mileage;
    private Integer modelYear;
    private Integer buildYear;
    private String color;

}
