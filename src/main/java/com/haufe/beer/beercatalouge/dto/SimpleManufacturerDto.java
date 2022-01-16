package com.haufe.beer.beercatalouge.dto;

import com.haufe.beer.beercatalouge.model.Manufacturer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class SimpleManufacturerDto {

    private Long id;
    private String name;
    private String nationality;

    public static SimpleManufacturerDto from(Manufacturer manufacturer)
    {
        SimpleManufacturerDto manufacturerDto = new SimpleManufacturerDto();
        manufacturerDto.setId(manufacturer.getId());
        manufacturerDto.setName(manufacturer.getName());
        manufacturerDto.setNationality(manufacturer.getNationality());
      return manufacturerDto;
    }

}
