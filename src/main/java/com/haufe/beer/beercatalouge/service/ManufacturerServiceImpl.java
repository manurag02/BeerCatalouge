package com.haufe.beer.beercatalouge.service;

import com.haufe.beer.beercatalouge.api.ManufacturerService;
import com.haufe.beer.beercatalouge.exceptionhandling.BeerCatalogueGenericException;
import com.haufe.beer.beercatalouge.exceptionhandling.ManufacturerNotFoundException;
import com.haufe.beer.beercatalouge.model.Beer;
import com.haufe.beer.beercatalouge.model.Manufacturer;
import com.haufe.beer.beercatalouge.repository.ManufacturerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> getAllManufacturers(Integer pageNo, Integer pageSize) {

        Pageable page = PageRequest.of(pageNo,pageSize);
        Page<Manufacturer> manufacturerList = manufacturerRepository.findAll(page);

        if(!manufacturerList.hasContent())
        {
            throw new BeerCatalogueGenericException();
        }

        return manufacturerList.getContent();
    }

    @Override
    public Manufacturer addManufacturer(Manufacturer manufacturer) {

        var newManufacturer = manufacturerRepository.save(manufacturer);
        return newManufacturer;
    }

    @Override
    public Manufacturer updateManufacturer(Long manufacturerId, Manufacturer manufacturer) throws ManufacturerNotFoundException {

        return null;
    }

    @Override
    public Manufacturer addBeer(Long manufacturerId, Beer beer) throws ManufacturerNotFoundException {
        return null;
    }

    @Override
    public Manufacturer removeBeer(Long manufacturerId, Beer beer) throws ManufacturerNotFoundException {
        return null;
    }


    @Override
    public Manufacturer getManufacturer(Long manufacturerId) throws ManufacturerNotFoundException {
        return manufacturerRepository.findById(manufacturerId).orElseThrow(() -> new ManufacturerNotFoundException());
    }


    @Override
    public String deleteManufacturer(Long manufacturerId) throws ManufacturerNotFoundException {
        Manufacturer manufacturer = getManufacturer(manufacturerId);
        manufacturerRepository.delete(manufacturer);
        return "Deleted";
    }
}
