package de.lyth.vehicleapi.model.price;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class Price {

    private String currency;
    private BigDecimal price;
    private Long vehicleID;

    public Price() { }

}
