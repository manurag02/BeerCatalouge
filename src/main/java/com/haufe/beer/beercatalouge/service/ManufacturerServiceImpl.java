package com.haufe.beer.beercatalouge.service;

import com.haufe.beer.beercatalouge.api.ManufacturerService;
import com.haufe.beer.beercatalouge.exceptionhandling.ManufacturerNotFoundException;
import com.haufe.beer.beercatalouge.model.Manufacturer;
import com.haufe.beer.beercatalouge.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private ManufacturerRepository manufacturerRepository;


    @Autowired
    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
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
    @Transactional
    public Manufacturer addManufacturer(Manufacturer manufacturer) {
        var newManufacturer = manufacturerRepository.save(manufacturer);
        return newManufacturer;
    }

    @Override
    @Transactional
    public Manufacturer updateManufacturer(Integer manufacturerId, Manufacturer manufacturer) throws ManufacturerNotFoundException {
         var existingManufacturer = getManufacturer(manufacturerId);
         existingManufacturer.setName(manufacturer.getName());
         existingManufacturer.setNationality(manufacturer.getNationality());
         manufacturer.getBeers().forEach(t -> t.setManufacturer(existingManufacturer));
         existingManufacturer.setBeers(manufacturer.getBeers());
        return manufacturerRepository.save(existingManufacturer);
    }


    @Override
    public Manufacturer getManufacturer(Integer manufacturerId) throws ManufacturerNotFoundException {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId).orElseThrow(() -> new ManufacturerNotFoundException());
         return manufacturer;
    }


    @Override
    public String deleteManufacturer(Integer manufacturerId) throws ManufacturerNotFoundException {
        Manufacturer manufacturer = getManufacturer(manufacturerId);
        manufacturerRepository.delete(manufacturer);
        return "Deleted";
    }
}
