package de.lyth.vehicleapi.repositories;

import de.lyth.vehicleapi.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> { }
