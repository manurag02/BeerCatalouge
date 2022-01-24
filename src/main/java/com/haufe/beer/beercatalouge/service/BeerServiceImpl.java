package com.haufe.beer.beercatalouge.service;

import com.haufe.beer.beercatalouge.api.BeerService;
import com.haufe.beer.beercatalouge.api.ManufacturerService;
import com.haufe.beer.beercatalouge.exceptionhandling.BeerNotFoundException;
import com.haufe.beer.beercatalouge.exceptionhandling.ManufacturerNotFoundException;
import com.haufe.beer.beercatalouge.model.Beer;
import com.haufe.beer.beercatalouge.model.Manufacturer;
import com.haufe.beer.beercatalouge.repository.BeerRepository;
import com.haufe.beer.beercatalouge.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BeerServiceImpl implements BeerService {

    private BeerRepository beerRepository;
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    public BeerServiceImpl(BeerRepository beerRepository, ManufacturerRepository manufacturerRepository)
    {
        this.beerRepository = beerRepository;
        this.manufacturerRepository = manufacturerRepository;
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
    public List<Beer> getAllBeersByType(String type,Integer pageNo, Integer pageSize) {
        Pageable page = PageRequest.of(pageNo,pageSize);
        List<Beer> beerList = beerRepository.getBeersByType(type,page).getContent();

        if(beerList.isEmpty())
        {
            throw new BeerNotFoundException();
        }

        return beerList;
    }

    @Override
    public Beer getBeer(Integer id) throws BeerNotFoundException {
        return beerRepository.findById(id).orElseThrow(() -> new BeerNotFoundException());
    }

    @Override
    @Transactional
    public Beer addBeer(Beer beer) {

        Optional<Manufacturer> existingManufacturer = manufacturerRepository.findById(beer.getManufacturer().getId());
        if(!existingManufacturer.isPresent())
        {
            throw new ManufacturerNotFoundException();
        }
        beer.setManufacturer(existingManufacturer.get());
        return beerRepository.save(beer);
    }

    @Override
    @Transactional
    public Beer updateBeer(Integer beerId, Beer beer) throws BeerNotFoundException, ManufacturerNotFoundException {

        Optional<Manufacturer> existingManufacturer = manufacturerRepository.findById(beer.getManufacturer().getId());
        if(!existingManufacturer.isPresent())
        {
            throw new ManufacturerNotFoundException();
        }
        var existingBeer = getBeer(beerId);
        existingBeer.setType(beer.getType());
        existingBeer.setDescription(beer.getDescription());
        existingBeer.setGraduation(beer.getGraduation());
        existingBeer.setManufacturer(existingManufacturer.get());
        return beerRepository.save(existingBeer);
    }

    @Override
    public String deleteBeer(Integer beerId) throws BeerNotFoundException {
        Beer beer = getBeer(beerId);
        beerRepository.delete(beer);
        return "Beer Deleted";
    }

}
