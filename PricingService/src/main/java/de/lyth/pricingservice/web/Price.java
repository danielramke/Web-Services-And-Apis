package de.lyth.pricingservice.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * This is a model for the price object which is sent.
 */

@Setter
@Getter
@AllArgsConstructor
@Entity
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String currency;
    private BigDecimal price;
    private Long vehicleID;

    public Price() { }

}
