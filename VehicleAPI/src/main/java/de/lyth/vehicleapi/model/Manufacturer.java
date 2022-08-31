package de.lyth.vehicleapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@Entity
public class Manufacturer {

    @Id
    private Integer code;

    private String name;

    public Manufacturer() { }

}
