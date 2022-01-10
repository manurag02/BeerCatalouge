package com.haufe.beer.beercatalouge.api;

import com.haufe.beer.beercatalouge.model.Beer;

import java.util.List;

public interface BeerService {

    List<Beer> getAllBeers(Integer pageNo, Integer pageSize);

    List<Beer> getAllBeersByManufacturerName(String manufacturerName);

    List<Beer> getAllBeersByType(String type);

    Beer getBeerByName(String beerName);


}
