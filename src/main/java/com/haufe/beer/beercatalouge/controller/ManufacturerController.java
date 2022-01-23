package com.haufe.beer.beercatalouge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haufe.beer.beercatalouge.api.ManufacturerService;
import com.haufe.beer.beercatalouge.dto.ManufacturerDto;
import com.haufe.beer.beercatalouge.model.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/manufacturers")
public class ManufacturerController {

    private ManufacturerService manufacturerServiceImpl;

    private ObjectMapper objectMapper;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerServiceImpl, ObjectMapper objectMapper)
    {
        this.manufacturerServiceImpl = manufacturerServiceImpl;
        this.objectMapper = objectMapper;
    }

    /**
     * Get beer list by page number and page size (default 0,10)
     * @param pageNo - Page to be viewed
     * @param pageSize - Page size of the page to be viewed
     * @return the response entity with List of manufacturers.
     */
    @GetMapping()
    public ResponseEntity<List<ManufacturerDto>> getAllManufacturers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") @Valid Integer pageSize
    ) {
         var manufacturers = manufacturerServiceImpl.getAllManufacturers(pageNo,pageSize);
         List<ManufacturerDto> manufacturerDtos = manufacturers.stream().map(t -> objectMapper.convertValue(t,ManufacturerDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(manufacturerDtos, HttpStatus.OK);
    }

    /**
     * Get stock list by page number and page size (default 0,10)
     * @param manufacturerId - manufacturerName to be found
     * @return the response entity with the manufacturer.
     */
    @GetMapping("/{manufacturerId}")
    public ResponseEntity<ManufacturerDto> getManufacturer(
            @PathVariable("manufacturerId") Integer manufacturerId
    ) {
        var manufacturer = manufacturerServiceImpl.getManufacturer(manufacturerId);
        return new ResponseEntity<>(objectMapper.convertValue(manufacturer,ManufacturerDto.class),HttpStatus.OK);
    }

    /**
     * Create Manufacturer entity.
     * @param manufacturerDto - manufacturer to be created
     * @return the response entity with the created manufacturer.
     */
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ManufacturerDto> addManufacturer(
            @RequestBody ManufacturerDto manufacturerDto) {

        var manufacturer = objectMapper.convertValue(manufacturerDto, Manufacturer.class);
         manufacturerServiceImpl.addManufacturer(manufacturer);
        return new ResponseEntity<>(objectMapper.convertValue(manufacturer,ManufacturerDto.class),
                HttpStatus.CREATED);
    }

    /**
     * Update Manufacturer entity.
     * @param manufacturerDto - manufacturer to be created
     * @return the response entity with the created manufacturer.
     */
    @PutMapping(value = "/{manufacturerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ManufacturerDto> updateManufacturer(
            @PathVariable("manufacturerId") Integer manufacturerId,
             @RequestBody ManufacturerDto manufacturerDto) {
        var manufacturer = objectMapper.convertValue(manufacturerDto, Manufacturer.class);
         manufacturerServiceImpl.updateManufacturer(manufacturerId, manufacturer);
        return new ResponseEntity<>(objectMapper.convertValue(manufacturer,ManufacturerDto.class),
                HttpStatus.OK);
    }


    /**
     * Delete Manufacturer entity.
     * @param manufacturerId - manufacturer to be created
     * @return the response entity with the created manufacturer.
     */
    @DeleteMapping(value = "/{manufacturerId}")
    public ResponseEntity<String> deleteManufacturer(
            @PathVariable("manufacturerId") Integer manufacturerId) {
        String manufacturerDeleteMessage = manufacturerServiceImpl.deleteManufacturer(manufacturerId);
        return new ResponseEntity<>(manufacturerDeleteMessage,
                HttpStatus.OK);
    }
}
