package de.lyth.vehicleapi.model.price;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class PriceList {

    private List<Price> prices;

    public PriceList() { }

    public void addPrice(Price price) {
        if(prices != null) prices.add(price);
    }
}
