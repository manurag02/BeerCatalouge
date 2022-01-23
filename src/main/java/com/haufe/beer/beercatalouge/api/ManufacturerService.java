package com.haufe.beer.beercatalouge.api;

import com.haufe.beer.beercatalouge.exceptionhandling.ManufacturerNotFoundException;
import com.haufe.beer.beercatalouge.model.Beer;
import com.haufe.beer.beercatalouge.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {

     List<Manufacturer> getAllManufacturers(Integer pageNo, Integer pageSize);

     Manufacturer addManufacturer(Manufacturer manufacturer);

     Manufacturer updateManufacturer(Integer manufacturerId, Manufacturer manufacturer) throws ManufacturerNotFoundException;

     Manufacturer getManufacturer(Integer manufacturerId) throws ManufacturerNotFoundException;

     String deleteManufacturer(Integer manufacturerId) throws ManufacturerNotFoundException;

}
