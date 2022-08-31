package de.lyth.vehicleapi.proxy;

import de.lyth.vehicleapi.model.price.Price;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "pricing-service")
public interface PriceProxy {

    @GetMapping("/prices/search/findByVehicleID")
    Price getPrice(@RequestParam Long vehicleID);

    @PostMapping("/prices")
    Price save(@RequestParam Price price);

    @DeleteMapping("/prices/{id}")
    Price delete(@PathVariable Long id);

}
