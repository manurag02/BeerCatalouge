package com.haufe.beer.beercatalouge.controller;

import com.haufe.beer.beercatalouge.api.BeerService;
import com.haufe.beer.beercatalouge.dto.BeerDto;
import com.haufe.beer.beercatalouge.model.Beer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {

    private BeerService beerService;

    public BeerController(BeerService beerService)
    {
        this.beerService = beerService;
    }

    /**
     * Get Beer list by page number and page size (default 0,10)
     * @param pageNo - Page to be viewed
     * @param pageSize - Page size of the page to be viewed
     * @return the response entity with List of beers.
     */
    @GetMapping()
    public ResponseEntity<List<BeerDto>> getAllBeers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") @Valid Integer pageSize
    ) {
        var beerDtoList = beerService.getAllBeers(pageNo,pageSize).stream().map(BeerDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(beerDtoList, HttpStatus.OK);
    }

    /**
     * Get Beer list by manufacturer name with  page number and page size (default 0,10)
     * @param manufacturerName - manufacturerName from which beers to be found
     * @return the response entity with the list of beers.
     */
    @GetMapping("/{manufacturerName}/manufacturer")
    public ResponseEntity<List<BeerDto>> getAllBeersByManufacturerName(
            @PathVariable("manufacturerName") String manufacturerName,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") @Valid Integer pageSize
    ) {
        var beerDtoList =  beerService.getAllBeersByManufacturerName(manufacturerName,pageNo,pageSize).stream().map(BeerDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(beerDtoList, HttpStatus.OK);
    }

    /**
     * Get Beer list by beer type
     * @param type - type of beer from which beers to be found
     * @return the response entity with the list of beers.
     */
    @GetMapping("/{type}/type")
    public ResponseEntity<List<BeerDto>> getAllBeersByType(
            @PathVariable("type") String type,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") @Valid Integer pageSize
    ) {
        var beerDtoList =  beerService.getAllBeersByType(type,pageNo,pageSize).stream().map(BeerDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(beerDtoList, HttpStatus.OK);
    }

    /**
     * Get Beer by beer name
     * @param name -  beer from its name
     * @return the response entity with the list of beers.
     */
    @GetMapping("/{name}/name")
    public ResponseEntity<BeerDto> getBeerByName(
            @PathVariable("name") String name
    ) {
        var beerDto = BeerDto.from(beerService.getBeerByName(name));
        return new ResponseEntity<>(beerDto, HttpStatus.OK);
    }
}
