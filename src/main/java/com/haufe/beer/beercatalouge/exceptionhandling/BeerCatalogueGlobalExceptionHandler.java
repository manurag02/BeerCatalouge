package com.haufe.beer.beercatalouge.exceptionhandling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class BeerCatalogueGlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    /**
     * The Error constants.
     */
    private final ErrorConstants errorConstants;

    /**
     *
     * Handle in valid game id exception response entity. DataIntegrityViolationException
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Error> handleConstraintViolationException(ConstraintViolationException ex) {


        Error error = getErrorResponseEntity(errorConstants.getConstraintViolationException(), HttpStatus.BAD_REQUEST, ex);

        log.error("{} :: {}", errorConstants.getConstraintViolationException().getCode(),
                errorConstants.getConstraintViolationException().getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle DataIntegrityViolationException
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Error> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {


        Error error = getErrorResponseEntity(errorConstants.getDataIntegrityViolationException(), HttpStatus.BAD_REQUEST, ex);

        log.error("{} :: {}", errorConstants.getDataIntegrityViolationException().getCode(),
                errorConstants.getDataIntegrityViolationException().getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle IllegalArgumentException.class
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Error> handleDataIntegrityViolationException(IllegalArgumentException ex) {


        Error error = getErrorResponseEntity(errorConstants.getConstraintViolationException(), HttpStatus.BAD_REQUEST, ex);

        log.error("{} :: {}", errorConstants.getConstraintViolationException().getCode(),
                errorConstants.getConstraintViolationException().getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle ManufacturerNotFound Exception
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(ManufacturerNotFoundException.class)
    public ResponseEntity<Error> handleBeerCatalogueGenericException(ManufacturerNotFoundException ex) {


        Error error = getErrorResponseEntity(errorConstants.getManufacturerNotFoundException(), HttpStatus.NOT_FOUND, ex);

        log.error("{} :: {}", errorConstants.getManufacturerNotFoundException().getCode(),
                errorConstants.getManufacturerNotFoundException().getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle BeerNotFound Exception
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(BeerNotFoundException.class)
    public ResponseEntity<Error> handleBeerCatalogueGenericException(BeerNotFoundException ex) {


        Error error = getErrorResponseEntity(errorConstants.getBeerNotFoundException(), HttpStatus.NOT_FOUND, ex);

        log.error("{} :: {}", errorConstants.getBeerNotFoundException().getCode(),
                errorConstants.getBeerNotFoundException().getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Gets error response entity.
     *
     * @param errorAttributes the error attributes
     * @param httpStatus      the http status
     * @param ex              the ex
     * @return the error response entity
     */
    private Error getErrorResponseEntity(ErrorAttributes errorAttributes, HttpStatus httpStatus, Exception ex) {

        Error error = new Error();
        error.setErrors(
                List.of(ErrorAttributes.builder().code(errorAttributes.getCode()).message(errorAttributes.getMessage())
                        .status(httpStatus.value()).params(List.of(ex.getClass().getSimpleName())).build()));

        return error;
    }
}
