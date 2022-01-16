package com.haufe.beer.beercatalouge.exceptionhandling;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("constants.error")
public class ErrorConstants {

    private ErrorAttributes manufacturerNotFoundException;
    private ErrorAttributes beerNotFoundException;
    private ErrorAttributes constraintViolationException;
    private ErrorAttributes dataIntegrityViolationException;

}
