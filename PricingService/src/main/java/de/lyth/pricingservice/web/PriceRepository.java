package de.lyth.pricingservice.web;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "prices", path = "prices")
public interface PriceRepository extends CrudRepository<Price, Long> {

    Price findByVehicleID(@Param("vehicleID") Long vehicleID);

}
