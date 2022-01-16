package com.haufe.beer.beercatalouge.api;

import com.haufe.beer.beercatalouge.model.Beer;
import io.swagger.models.auth.In;

import java.util.List;

public interface BeerService {

    List<Beer> getAllBeers(Integer pageNo, Integer pageSize);

    List<Beer> getAllBeersByManufacturerName(String manufacturerName,Integer pageNo, Integer pageSize);

    List<Beer> getAllBeersByType(String type,Integer pageNo, Integer pageSize);

    Beer getBeerByName(String beerName);


}
