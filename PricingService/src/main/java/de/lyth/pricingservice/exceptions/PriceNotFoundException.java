package de.lyth.pricingservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This Exception throws if a price was not found, its include the HTTP error code 404
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The requested price can't be found!")
public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException() { }

    public PriceNotFoundException(String message) {
        super(message);
    }

}
