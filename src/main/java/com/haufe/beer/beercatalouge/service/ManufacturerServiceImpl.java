package com.haufe.beer.beercatalouge.service;

import com.haufe.beer.beercatalouge.api.BeerService;
import com.haufe.beer.beercatalouge.api.ManufacturerService;
import com.haufe.beer.beercatalouge.exceptionhandling.BeerAlreadyAddedException;
import com.haufe.beer.beercatalouge.exceptionhandling.BeerCatalogueGenericException;
import com.haufe.beer.beercatalouge.exceptionhandling.ManufacturerNotFoundException;
import com.haufe.beer.beercatalouge.model.Beer;
import com.haufe.beer.beercatalouge.model.Manufacturer;
import com.haufe.beer.beercatalouge.repository.BeerRepository;
import com.haufe.beer.beercatalouge.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private ManufacturerRepository manufacturerRepository;

    private BeerService beerServiceImpl;

    @Autowired
    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository, BeerService beerServiceImpl) {
        this.manufacturerRepository = manufacturerRepository;
        this.beerServiceImpl = beerServiceImpl;
    }

    @Override
    public List<Manufacturer> getAllManufacturers(Integer pageNo, Integer pageSize) {

        Pageable page = PageRequest.of(pageNo,pageSize);
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll(page).getContent();

        if(manufacturerList.isEmpty())
        {
            throw new ManufacturerNotFoundException();
        }

        return manufacturerList;
    }

    @Override
    public Manufacturer addManufacturer(Manufacturer manufacturer) {

        var newManufacturer = manufacturerRepository.save(manufacturer);
        return newManufacturer;
    }

    @Override
    public Manufacturer updateManufacturer(Long manufacturerId, Manufacturer manufacturer) throws ManufacturerNotFoundException {
        Manufacturer existingManufacturer = getManufacturer(manufacturerId);
        existingManufacturer.setName(manufacturer.getName());
        existingManufacturer.setNationality(manufacturer.getNationality());
        var updatedManufacturer = manufacturerRepository.save(existingManufacturer);
        return updatedManufacturer;
    }

    @Override
    @Transactional
    public Manufacturer addBeer(Long manufacturerId, Beer beer) throws ManufacturerNotFoundException {
        Manufacturer manufacturer = getManufacturer(manufacturerId);
        Beer beerToBeAdded = beerServiceImpl.getBeer(beer.getBeerId());
        if(Objects.nonNull(beerToBeAdded.getManufacturer())){
            throw new BeerAlreadyAddedException();
        }
        manufacturer.addBeer(beer);
        beerToBeAdded.setManufacturer(manufacturer);
        return manufacturer;
    }

    @Override
    public Manufacturer removeBeer(Long manufacturerId, Beer beer) throws ManufacturerNotFoundException {
        return null;
    }


    @Override
    public Manufacturer getManufacturer(Long manufacturerId) throws ManufacturerNotFoundException {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId).orElseThrow(() -> new ManufacturerNotFoundException());
        var beerList = beerServiceImpl.getAllBeersByManufacturerName(manufacturer.getName(),0,10);
         for(Beer beer: beerList)
         {
             manufacturer.addBeer(beer);
         }
         return manufacturer;
    }


    @Override
    public String deleteManufacturer(Long manufacturerId) throws ManufacturerNotFoundException {
        Manufacturer manufacturer = getManufacturer(manufacturerId);
        manufacturerRepository.delete(manufacturer);
        return "Deleted";
    }
}
