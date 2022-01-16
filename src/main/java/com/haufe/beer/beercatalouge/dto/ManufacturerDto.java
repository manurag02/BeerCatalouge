package com.haufe.beer.beercatalouge.dto;

import com.haufe.beer.beercatalouge.model.Manufacturer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class ManufacturerDto {

    private Long id;
    private String name;
    private String nationality;
    private List<BeerDto> beersDto = new ArrayList<>();

    public static ManufacturerDto from(Manufacturer manufacturer)
    {
        ManufacturerDto manufacturerDto = new ManufacturerDto();
        manufacturerDto.setId(manufacturer.getId());
        manufacturerDto.setName(manufacturer.getName());
        manufacturerDto.setNationality(manufacturer.getNationality());
        if(Objects.nonNull(manufacturer.getBeers()))
        {
            manufacturerDto.setBeersDto(manufacturer.getBeers().stream().map(BeerDto::from).collect(Collectors.toList()));
        }

      return manufacturerDto;
    }

}
