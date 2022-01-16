package com.haufe.beer.beercatalouge.service;

import com.haufe.beer.beercatalouge.api.BeerService;
import com.haufe.beer.beercatalouge.exceptionhandling.BeerCatalogueGenericException;
import com.haufe.beer.beercatalouge.exceptionhandling.BeerNotFoundException;
import com.haufe.beer.beercatalouge.model.Beer;
import com.haufe.beer.beercatalouge.model.Manufacturer;
import com.haufe.beer.beercatalouge.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerServiceImpl implements BeerService {

    private BeerRepository beerRepository;

    @Autowired
    public BeerServiceImpl(BeerRepository beerRepository)
    {
        this.beerRepository = beerRepository;
    }

    @Override
    public List<Beer> getAllBeers(Integer pageNo, Integer pageSize) {
        Pageable page = PageRequest.of(pageNo,pageSize);
        List<Beer> beerList = beerRepository.findAll(page).getContent();

        if(beerList.isEmpty())
        {
            throw new BeerNotFoundException();
        }

        return beerList;
    }

    @Override
    public List<Beer> getAllBeersByManufacturerName(String manufacturerName, Integer pageNo, Integer pageSize) {
        Pageable page = PageRequest.of(pageNo,pageSize);
        List<Beer> beerList = beerRepository.getBeerByManufacturerName(manufacturerName,page).getContent();

        if(beerList.isEmpty())
        {
            throw new BeerNotFoundException();
        }

        return beerList;
    }


    @Override
    public List<Beer> getAllBeersByType(String type,Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public Beer getBeer(Long id) throws BeerNotFoundException {
        return beerRepository.findById(id).orElseThrow(() -> new BeerNotFoundException());
    }

}
