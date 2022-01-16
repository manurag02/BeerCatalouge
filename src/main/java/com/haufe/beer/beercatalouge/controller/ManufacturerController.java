package com.haufe.beer.beercatalouge.controller;

import com.haufe.beer.beercatalouge.api.ManufacturerService;
import com.haufe.beer.beercatalouge.dto.BeerDto;
import com.haufe.beer.beercatalouge.dto.ManufacturerDto;
import com.haufe.beer.beercatalouge.model.Beer;
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

    @Autowired
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
    public ResponseEntity<List<ManufacturerDto>> getAllManufacturers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") @Valid Integer pageSize
    ) {
         var manufacturers = manufacturerServiceImpl.getAllManufacturers(pageNo,pageSize);
         List<ManufacturerDto> manufacturerDtos = manufacturers.stream().map(ManufacturerDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(manufacturerDtos, HttpStatus.OK);
    }

    /**
     * Get stock list by page number and page size (default 0,10)
     * @param manufacturerId - manufacturerName to be found
     * @return the response entity with the manufacturer.
     */
    @GetMapping("/{manufacturerId}")
    public ResponseEntity<ManufacturerDto> getManufacturer(
            @PathVariable("manufacturerId") Long manufacturerId
    ) {
        var manufacturer = manufacturerServiceImpl.getManufacturer(manufacturerId);
        return new ResponseEntity<>(ManufacturerDto.from(manufacturer),HttpStatus.OK);
    }

    /**
     * Create Manufacturer entity.
     * @param manufacturerDto - manufacturer to be created
     * @return the response entity with the created manufacturer.
     */
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ManufacturerDto> addManufacturer(
            @RequestBody ManufacturerDto manufacturerDto) {

        var manufacturer = manufacturerServiceImpl.addManufacturer(Manufacturer.from(manufacturerDto));
        return new ResponseEntity<>(ManufacturerDto.from(manufacturer),
                HttpStatus.CREATED);
    }

    /**
     * Update Manufacturer entity.
     * @param manufacturerDto - manufacturer to be created
     * @return the response entity with the created manufacturer.
     */
    @PutMapping(value = "/{manufacturerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ManufacturerDto> updateManufacturer(
            @PathVariable("manufacturerId") Long manufacturerId,
            @RequestBody ManufacturerDto manufacturerDto) {
        var manufacturer = manufacturerServiceImpl.updateManufacturer(manufacturerId, Manufacturer.from(manufacturerDto));
        return new ResponseEntity<>(ManufacturerDto.from(manufacturer),
                HttpStatus.OK);
    }

//    /**
//     * Update Manufacturer entity.
//     * @param beerDto to be added
//     * @return the response entity with the created manufacturer.
//     */
//    @PostMapping(value = "/{manufacturerId}/beers/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ManufacturerDto> addBeer(
//            @PathVariable("manufacturerId") Long manufacturerId,
//            @RequestBody BeerDto beerDto) {
//        var manufacturer = manufacturerServiceImpl.addBeer(manufacturerId, Beer.from(beerDto));
//        return new ResponseEntity<>(ManufacturerDto.from(manufacturer),
//                HttpStatus.OK);
//    }
//
//    /**
//     * Update Manufacturer entity.
//     * @param beerDto to be removed
//     * @return the response entity with the created manufacturer.
//     */
//    @PostMapping(value = "/{manufacturerId}/beers/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ManufacturerDto> removeBeer(
//            @PathVariable("manufacturerId") Long manufacturerId,
//            @RequestBody BeerDto beerDto) {
//        var manufacturer = manufacturerServiceImpl.removeBeer(manufacturerId, Beer.from(beerDto));
//        return new ResponseEntity<>(ManufacturerDto.from(manufacturer),
//                HttpStatus.OK);
//    }


    /**
     * Delete Manufacturer entity.
     * @param manufacturerId - manufacturer to be created
     * @return the response entity with the created manufacturer.
     */
    @DeleteMapping(value = "/{manufacturerId}")
    public ResponseEntity<String> deleteManufacturer(
            @PathVariable("manufacturerId") Long manufacturerId) {
        String manufacturerDeleteMessage = manufacturerServiceImpl.deleteManufacturer(manufacturerId);
        return new ResponseEntity<>(manufacturerDeleteMessage,
                HttpStatus.OK);
    }
}
