package de.lyth.vehicleapi.service;

import de.lyth.vehicleapi.exceptions.CarNotFoundException;
import de.lyth.vehicleapi.model.car.Car;
import de.lyth.vehicleapi.repositories.CarRepository;
import de.lyth.vehicleapi.service.component.client.MapsClient;
import de.lyth.vehicleapi.service.component.client.PriceClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class controls the car handling. This means with this class
 * you can create, read, update or delete cars.
 */
@AllArgsConstructor
@Service
public class CarService {

    private final CarRepository repository;
    private final MapsClient mapsClient;
    private final PriceClient priceClient;

    /**
     * This method gets the list of cars which exists.
     * The car will be contained a random address.
     * @return the car list.
     */
    public List<Car> list() {
        List<Car> cars = repository.findAll();
        List<Car> results = new ArrayList<>();
        for(Car car : cars) {
            car.setLocation(mapsClient.getAddress(car.getLocation()));
            car.setPrice(priceClient.getPrice(car.getId()));
            results.add(car);
        }
        return results;
    }

    /**
     * This method find a car with the given id.
     * The car will be contained a random address.
     * @param id the identifier of the car.
     * @return the founded car.
     */
    public Car findByID(Long id) {
        Car car = repository.findById(id).orElseThrow(CarNotFoundException::new);
        car.setPrice(priceClient.getPrice(car.getId()));
        car.setLocation(mapsClient.getAddress(car.getLocation()));
        return car;
    }

    /**
     * This method saved a new car to the system.
     * You can use this method as update method too.
     * If the car id was found than will this method post an update to the system.
     * @param car the car which stored the information to update or create a car.
     * @return the new or updated car.
     */
    public Car save(Car car) {
        if(car.getId() != null && car.getId() > 0) {
            return repository.findById(car.getId())
                    .map(updated -> {
                        updated.setDetails(car.getDetails());
                        updated.setLocation(car.getLocation());
                        updated.setCondition(car.getCondition());
                        return  repository.save(updated);
                    }).orElseThrow(CarNotFoundException::new);
        }
        return repository.save(car);
    }

    /**
     * This method deleted an existing car from the system by the given id.
     * @param id the identifier of the car which will be deleted.
     */
    public void delete(Long id) {
        Optional<Car> toDelete = repository.findById(id);
        if(toDelete.isPresent()) {
            repository.delete(toDelete.get());
        } else {
            throw new CarNotFoundException();
        }
    }

}
