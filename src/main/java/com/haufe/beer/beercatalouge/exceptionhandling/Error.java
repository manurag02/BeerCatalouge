package com.haufe.beer.beercatalouge.exceptionhandling;

import lombok.Data;

import java.util.List;

@Data
public class Error {

    /**
     * The Errors.
     */
    List<ErrorAttributes> errors;
}
