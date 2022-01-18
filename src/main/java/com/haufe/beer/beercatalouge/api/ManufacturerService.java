package com.haufe.beer.beercatalouge.api;

import com.haufe.beer.beercatalouge.exceptionhandling.ManufacturerNotFoundException;
import com.haufe.beer.beercatalouge.model.Beer;
import com.haufe.beer.beercatalouge.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {

     List<Manufacturer> getAllManufacturers(Integer pageNo, Integer pageSize);

     Manufacturer addManufacturer(Manufacturer manufacturer);

     Manufacturer updateManufacturer(Long manufacturerId, Manufacturer manufacturer) throws ManufacturerNotFoundException;

     Manufacturer getManufacturer(Long manufacturerId) throws ManufacturerNotFoundException;

     String deleteManufacturer(Long manufacturerId) throws ManufacturerNotFoundException;

}
