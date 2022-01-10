package com.haufe.beer.beercatalouge.service;

import com.haufe.beer.beercatalouge.api.ManufacturerService;
import com.haufe.beer.beercatalouge.exceptionhandling.ManufacturerNotFoundException;
import com.haufe.beer.beercatalouge.model.Beer;
import com.haufe.beer.beercatalouge.model.Manufacturer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Override
    public List<Manufacturer> getAllManufacturers(Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public Manufacturer addManufacturer(Manufacturer manufacturer) {
        return null;
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
    public Manufacturer getManufacturerByName(String manufacturerName) throws ManufacturerNotFoundException {
        return null;
    }


    @Override
    public String deleteManufacturer(Long manufacturerId) throws ManufacturerNotFoundException {
        return null;
    }
}
