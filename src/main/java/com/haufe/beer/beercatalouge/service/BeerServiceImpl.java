package com.haufe.beer.beercatalouge.service;

import com.haufe.beer.beercatalouge.api.BeerService;
import com.haufe.beer.beercatalouge.model.Beer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerServiceImpl implements BeerService {

    @Override
    public List<Beer> getAllBeers(Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public List<Beer> getAllBeersByManufacturerName(String manufacturerName,Integer pageNo, Integer pageSize ) {
        return null;
    }

    @Override
    public List<Beer> getAllBeersByType(String type,Integer pageNo, Integer pageSize) {
        return null;
    }


    @Override
    public Beer getBeerByName(String beerName) {
        return null;
    }
}
