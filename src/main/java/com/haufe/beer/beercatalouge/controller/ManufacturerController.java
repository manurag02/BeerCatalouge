package com.haufe.beer.beercatalouge.controller;

import com.haufe.beer.beercatalouge.api.ManufacturerService;
import com.haufe.beer.beercatalouge.model.Beer;
import com.haufe.beer.beercatalouge.model.Manufacturer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/manufacturers")
public class ManufacturerController {

    private ManufacturerService manufacturerServiceImpl;

    public ManufacturerController(ManufacturerService manufacturerServiceImpl)
    {
        this.manufacturerServiceImpl = manufacturerServiceImpl;
    }

    /**
     * Get beer list by page number and page size (default 0,10)
     * @param pageNo - Page to be viewed
     * @param pageSize - Page size of the page to be viewed
     * @return the response entity with List of manufacturers.
     */
    @GetMapping()
    public List<Manufacturer> getAllManufacturers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") @Valid Integer pageSize
    ) {
        return manufacturerServiceImpl.getAllManufacturers(pageNo,pageSize);
    }

    /**
     * Get stock list by page number and page size (default 0,10)
     * @param manufacturerName - manufacturerName to be found
     * @return the response entity with the manufacturer.
     */
    @GetMapping("/{manufacturerName}")
    public Manufacturer getManufacturerByName(
            @PathVariable("manufacturerName") String manufacturerName
    ) {
        return manufacturerServiceImpl.getManufacturerByName(manufacturerName);
    }

    /**
     * Create Manufacturer entity.
     * @param manufacturer - manufacturer to be created
     * @return the response entity with the created manufacturer.
     */
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Manufacturer> addManufacturer(
            @RequestBody Manufacturer manufacturer) {
        return new ResponseEntity<>(manufacturerServiceImpl.addManufacturer(manufacturer),
                HttpStatus.CREATED);
    }

    /**
     * Update Manufacturer entity.
     * @param manufacturer - manufacturer to be created
     * @return the response entity with the created manufacturer.
     */
    @PutMapping(value = "/{manufacturerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Manufacturer> updateManufacturer(
            @PathVariable("manufacturerId") Long manufacturerId,
            @RequestBody Manufacturer manufacturer) {
        return new ResponseEntity<>(manufacturerServiceImpl.updateManufacturer(manufacturerId, manufacturer),
                HttpStatus.OK);
    }

    /**
     * Update Manufacturer entity.
     * @param beer to be added
     * @return the response entity with the created manufacturer.
     */
    @PatchMapping(value = "/{manufacturerId}/beers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Manufacturer> addBeer(
            @PathVariable("manufacturerId") Long manufacturerId,
            @RequestBody Beer beer) {
        return new ResponseEntity<>(manufacturerServiceImpl.addBeer(manufacturerId, beer),
                HttpStatus.OK);
    }


    /**
     * Delete Manufacturer entity.
     * @param manufacturerId - manufacturer to be created
     * @return the response entity with the created manufacturer.
     */
    @DeleteMapping(value = "/{manufacturerId}")
    public ResponseEntity<?> deleteManufacturer(
            @PathVariable("manufacturerId") Long manufacturerId) {
        return new ResponseEntity<>(manufacturerServiceImpl.deleteManufacturer(manufacturerId),
                HttpStatus.OK);
    }
}
