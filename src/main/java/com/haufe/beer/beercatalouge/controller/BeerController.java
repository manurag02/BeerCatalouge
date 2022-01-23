package com.haufe.beer.beercatalouge.controller;

import com.haufe.beer.beercatalouge.api.BeerService;
import com.haufe.beer.beercatalouge.dto.BeerDto;
import com.haufe.beer.beercatalouge.dto.ManufacturerDto;
import com.haufe.beer.beercatalouge.model.Beer;
import com.haufe.beer.beercatalouge.model.Manufacturer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
     * @param id - id of the beer
     * @return the response entity with the list of beers.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BeerDto> getBeer(
            @PathVariable("id") Integer id
    ) {
        var beerDto = BeerDto.from(beerService.getBeer(id));
        return new ResponseEntity<>(beerDto, HttpStatus.OK);
    }

    /**
     * Create Beer entity.
     * @param beerDto - beer to be created
     * @return the response entity with the created beer.
     */
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BeerDto> addBeer(
           @RequestBody BeerDto beerDto) {

        var beer = beerService.addBeer(Beer.from(beerDto));
        return new ResponseEntity<>(BeerDto.from(beer),
                HttpStatus.CREATED);
    }

    /**
     * Update Beer entity.
     * @param beerDto - Beer to be created
     * @return the response entity with the created Beer.
     */
    @PutMapping(value = "/{beerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BeerDto> updateBeer(
            @PathVariable("beerId") Integer beerId,
            @RequestBody BeerDto beerDto) {
        var beer = beerService.updateBeer(beerId, Beer.from(beerDto));
        return new ResponseEntity<>(BeerDto.from(beer),
                HttpStatus.OK);
    }

    /**
     * Delete Beer entity.
     * @param beerId - Beer to be created
     * @return the response entity with the created Beer.
     */
    @DeleteMapping(value = "/{beerId}")
    public ResponseEntity<String> deleteBeer(
            @PathVariable("beerId") Integer beerId) {
        String BeerDeleteMessage = beerService.deleteBeer(beerId);
        return new ResponseEntity<>(BeerDeleteMessage,
                HttpStatus.OK);
    }
}
