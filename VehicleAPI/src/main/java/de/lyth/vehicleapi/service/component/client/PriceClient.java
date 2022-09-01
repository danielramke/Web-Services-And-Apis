package de.lyth.vehicleapi.service.component.client;

import de.lyth.vehicleapi.model.price.Price;
import de.lyth.vehicleapi.proxy.PriceProxy;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This class is used for getting price information.
 */
@AllArgsConstructor
@Component
public class PriceClient {

    private static final Logger logger = LoggerFactory.getLogger(PriceClient.class);

    private final PriceProxy proxy;

    /**
     * This method gets the price which is stored in the h2 database by the vehicle id.
     * @param vehicleID the identifier for the price.
     * @return the price for the requested vehicle.
     * If the vehicle not found the price will contain an error message.
     */
    public String getPrice(Long vehicleID) {
        try {
            Price price = proxy.getPrice(vehicleID);
            return String.format("%s %s", price.getCurrency(), price.getPrice());
        } catch (Exception exception) {
            logger.error("Unchecked error by catch the price for vehicle! {}", vehicleID, exception);
        }
        return "(no price)";
    }

}
