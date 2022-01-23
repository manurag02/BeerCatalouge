package com.haufe.beer.beercatalouge.api;

import com.haufe.beer.beercatalouge.exceptionhandling.BeerNotFoundException;
import com.haufe.beer.beercatalouge.exceptionhandling.ManufacturerNotFoundException;
import com.haufe.beer.beercatalouge.model.Beer;

import java.util.List;

public interface BeerService {

    List<Beer> getAllBeers(Integer pageNo, Integer pageSize);

    List<Beer> getAllBeersByType(String type, Integer pageNo, Integer pageSize);

    Beer getBeer(Integer id) throws BeerNotFoundException;

    Beer addBeer(Beer beer);

    Beer updateBeer(Integer beerId, Beer beer) throws BeerNotFoundException, ManufacturerNotFoundException;

    String deleteBeer(Integer beerId) throws BeerNotFoundException;


}
