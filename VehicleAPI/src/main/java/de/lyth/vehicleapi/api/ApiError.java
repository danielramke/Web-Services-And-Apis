package de.lyth.vehicleapi.api;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * This record is used to model an api error object.
 * @param message - the error message.
 * @param errors - the errors as list.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiError(String message, List<String> errors) { }
