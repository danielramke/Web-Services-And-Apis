package de.lyth.vehicleapi.repositories;

import de.lyth.vehicleapi.model.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> { }
