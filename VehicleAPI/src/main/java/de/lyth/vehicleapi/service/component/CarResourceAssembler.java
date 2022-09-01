package de.lyth.vehicleapi.service.component;

import de.lyth.vehicleapi.controller.CarController;
import de.lyth.vehicleapi.model.car.Car;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * This class using HATEOAS for mapping the car controller to the car class.
 * Note that the HATEOAS was change in the new version of 2.7.3 and have many changes.
 * TODO: look for issues by working with HATEOAS.
 */

@Component
public class CarResourceAssembler implements RepresentationModelAssembler<Car, EntityModel<Car>> {

    @Override
    public EntityModel<Car> toModel(Car car) {
        return EntityModel.of(car,
                linkTo(methodOn(CarController.class).get(car.getId())).withSelfRel(),
                linkTo(methodOn(CarController.class).list()).withRel("cars"));
    }
}
