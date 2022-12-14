package de.lyth.vehicleapi.service.component.client;

import de.lyth.vehicleapi.model.Address;
import de.lyth.vehicleapi.model.Location;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

/**
 * This class is used for getting random locations.
 */
@AllArgsConstructor
@Component
public class MapsClient {

    private static final Logger logger = LoggerFactory.getLogger(MapsClient.class);

    private final WebClient client;
    private final ModelMapper mapper;

    /**
     * This method gets an address from the Maps client, given latitude and longitude.
     * @param location an object which contains the lat and lon.
     * @return An updated location including street, city, state and zip,
     *  or an exception message noting the Maps service is down
     */
    public Location getAddress(Location location) {
        try {
            Address address = client.get()
                    .uri(builder ->
                        builder.path("/maps/")
                                .queryParam("lat", location.getLat())
                                .queryParam("lon", location.getLon())
                                .build()
                    ).retrieve().bodyToMono(Address.class).block();

            mapper.map(Objects.requireNonNull(address), location);
            return location;
        } catch (Exception exception) {
            logger.warn("Map service is not found or is down");
            return location;
        }
    }

}
