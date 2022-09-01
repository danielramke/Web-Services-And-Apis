package de.lyth.vehicleapi.api;

import de.lyth.vehicleapi.model.Condition;
import de.lyth.vehicleapi.model.Location;
import de.lyth.vehicleapi.model.Manufacturer;
import de.lyth.vehicleapi.model.car.Car;
import de.lyth.vehicleapi.model.car.CarDetails;
import de.lyth.vehicleapi.service.CarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Implements testing of the CarController class.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class CarTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<Car> json;

    @MockBean
    private CarService service;

    @Before
    public void setupTest() {
        Car car = getCar();
        car.setId(1L);
        given(service.save(any())).willReturn(car);
        given(service.findByID(any())).willReturn(car);
        given(service.list()).willReturn(Collections.singletonList(car));
    }

    /*
    Test Area
     */

    /**
     * This tested for successful creation of a new car.
     * @throws Exception when car creation fails in the system
     */
    @Test
    public void createCar() throws Exception {
        Car car = getCar();
        mvc.perform(
                post(new URI("/cars"))
                        .content(json.write(car).getJson())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    /**
     * This test returned a list with all cars.
     * The list is checked by json formats and if it presents.
     * @throws Exception if it was not found any cars.
     */
    @Test
    public void listCar() throws Exception {
        Car car = getCar();

        mvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.carList.[0].condition").value(car.getCondition().toString()))
                .andExpect(jsonPath("$._embedded.carList.[0].details.body").value(car.getDetails().getBody()))
                .andExpect(jsonPath("$._embedded.carList.[0].details.model").value(car.getDetails().getModel()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("{}"));

        verify(service, times(1)).list();
    }

    /**
     * This test searched for a car directly by the id of 1.
     * It will check if the json format right and present.
     * @throws Exception if the car wasn't found by the id of 1.
     */
    @Test
    public void findCar() throws Exception {
        Car car = getCar();

        mvc.perform(get("/cars/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.details.color").value(car.getDetails().getColor()))
                .andExpect(jsonPath("$.details.doors").value(car.getDetails().getDoors()))
                .andExpect(jsonPath("$.details.body").value(car.getDetails().getBody()))
                .andExpect(jsonPath("$.details.model").value(car.getDetails().getModel()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("{}"));

        verify(service, times(1)).findByID(1L);

    }

    /**
     * This test updates an existing car and change his condition.
     * @throws Exception if the car wasn't found or can't be updated.
     */
    @Test
    public void updateCar() throws Exception {
        Car car = getCar();
        car.setId(1L);
        car.setCondition(Condition.USED);

        mvc.perform(put("/cars/1")
                .content(json.write(car).getJson())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.condition").value(car.getCondition().toString()));
    }

    /**
     * This test deleted a car for text the result.
     * @throws Exception if the car can't be deleted.
     */
    @Test
    public void deleteCar() throws Exception {
        Car car = getCar();
        car.setId(1L);
        mvc.perform(delete("/cars/"+ car.getId()))
                .andExpect(status().isNoContent());
        verify(service, times(1)).delete(1L);
    }

    /**
     * @return an example car for testing.
     */
    private Car getCar() {
        Car car = new Car();
        car.setLocation(new Location(25.766452, 73.998263));
        CarDetails details = new CarDetails();
        Manufacturer manufacturer = new Manufacturer(100, "Audi");
        details.setManufacturer(manufacturer);
        details.setModel("R8");
        details.setMileage(2345);
        details.setColor("Black");
        details.setBody("hard");
        details.setEngine("7.6L V8");
        details.setTypeOfFuel("Gasoline");
        details.setModelYear(2014);
        details.setBuildYear(2015);
        details.setDoors(2);
        car.setDetails(details);
        car.setCondition(Condition.NEW);
        return car;

    }

}
