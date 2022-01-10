package com.haufe.beer.beercatalouge.controller;

import com.haufe.beer.beercatalouge.api.BeerService;
import com.haufe.beer.beercatalouge.model.Beer;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {

    private BeerService beerServiceImpl;

    public BeerController(BeerService beerServiceImpl)
    {
        this.beerServiceImpl = beerServiceImpl;
    }

    /**
     * Get Beer list by page number and page size (default 0,10)
     * @param pageNo - Page to be viewed
     * @param pageSize - Page size of the page to be viewed
     * @return the response entity with List of beers.
     */
    @GetMapping()
    public List<Beer> getAllBeers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") @Valid Integer pageSize
    ) {
        return beerServiceImpl.getAllBeers(pageNo,pageSize);
    }

    /**
     * Get Beer list by manufacturer name with  page number and page size (default 0,10)
     * @param manufacturerName - manufacturerName from which beers to be found
     * @return the response entity with the list of beers.
     */
    @GetMapping("/{manufacturerName}/manufacturer")
    public List<Beer> getAllBeersByManufacturerName(
            @PathVariable("manufacturerName") String manufacturerName
    ) {
        return beerServiceImpl.getAllBeersByManufacturerName(manufacturerName);
    }

    /**
     * Get Beer list by beer type
     * @param type - type of beer from which beers to be found
     * @return the response entity with the list of beers.
     */
    @GetMapping("/{type}/type")
    public List<Beer> getAllBeersByType(
            @PathVariable("type") String type
    ) {
        return beerServiceImpl.getAllBeersByType(type);
    }

    /**
     * Get Beer by beer name
     * @param name -  beer from its name
     * @return the response entity with the list of beers.
     */
    @GetMapping("/{name}/name")
    public Beer getBeerByName(
            @PathVariable("name") String name
    ) {
        return beerServiceImpl.getBeerByName(name);
    }
}
