package de.lyth.vehicleapi;

import de.lyth.vehicleapi.model.Manufacturer;
import de.lyth.vehicleapi.repositories.ManufacturerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableJpaAuditing
@EnableEurekaClient
@EnableFeignClients
public class VehicleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(ManufacturerRepository repository) {
        return arguments -> {
            repository.save(new Manufacturer(100, "Audi"));
            repository.save(new Manufacturer(101, "Chevrolet"));
            repository.save(new Manufacturer(102, "Ford"));
            repository.save(new Manufacturer(103, "BMW"));
            repository.save(new Manufacturer(104, "Dodge"));
        };
    }

    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }

    @Bean(name = "maps")
    public WebClient clientMaps(@Value("${maps.endpoint}") String endpoint) {
        return WebClient.create(endpoint);
    }

}
