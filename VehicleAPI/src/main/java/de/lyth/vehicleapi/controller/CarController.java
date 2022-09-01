package de.lyth.vehicleapi.controller;

import de.lyth.vehicleapi.exceptions.CarNotFoundException;
import de.lyth.vehicleapi.model.car.Car;
import de.lyth.vehicleapi.service.CarService;
import de.lyth.vehicleapi.service.component.CarResourceAssembler;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Implements the Rest controller for the Vehicles API.
 * This controller controls the mapping point (/cars).
 */
@AllArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService service;
    private final CarResourceAssembler assembler;

    /**
     * This method gets all existing cars' information.
     * @return a list of founded cars.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<EntityModel<Car>> list() {
        List<EntityModel<Car>> resources = service.list().stream().map(assembler::toModel).toList();
        return CollectionModel.of(resources,
                linkTo(methodOn(CarController.class).list()).withSelfRel());
    }

    /**
     * This method get the information from the car which was found by the given id.
     * @param id the identifier of the car.
     * @return all information about the car.
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<Car> get(@PathVariable Long id) {
        try {
            Car car = service.findByID(id);
            return assembler.toModel(car);
        } catch (Exception exception) {
            throw new CarNotFoundException("Car with id [ " + id + " ] wasn't found!");
        }
    }

    /**
     * This method add a new car with the posted information.
     * @param car the car as object.
     * @return response that the new car was added.
     * @throws URISyntaxException if the request contains invalid fields or syntax.
     */
    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody Car car) throws URISyntaxException {
        Car newCar = service.save(car);
        EntityModel<Car> resource = assembler.toModel(newCar);
        return ResponseEntity.created(new URI(resource.getRequiredLink("self").getHref())).body(resource);
    }

    /**
     * This method updated an existing car in the system.
     * @param id the identifier of the car.
     * @param car the car which stored the new information.
     * @return response that the car was updated.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @Valid @RequestBody Car car) {
        car.setId(id);
        service.save(car);
        EntityModel<Car> resource = assembler.toModel(car);
        return ResponseEntity.ok(resource);
    }

    /**
     * This method removed a car from the system.
     * @param id the identifier of the car which will be removed.
     * @return response that the car was deleted.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
        } catch (Exception exception) {
            throw new CarNotFoundException();
        }
        return ResponseEntity.noContent().build();
    }
}
