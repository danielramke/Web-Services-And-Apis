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

@AllArgsConstructor
@Service
public class CarService {

    private final CarRepository repository;
    private final MapsClient mapsClient;
    private final PriceClient priceClient;

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

    public Car findByID(Long id) {
        Car car = repository.findById(id).orElseThrow(CarNotFoundException::new);
        car.setPrice(priceClient.getPrice(car.getId()));
        car.setLocation(mapsClient.getAddress(car.getLocation()));
        return car;
    }

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

    public void delete(Long id) {
        Optional<Car> toDelete = repository.findById(id);
        if(toDelete.isPresent()) {
            repository.delete(toDelete.get());
        } else {
            throw new CarNotFoundException();
        }
    }

}
