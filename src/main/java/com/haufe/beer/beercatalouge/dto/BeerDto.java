package com.haufe.beer.beercatalouge.dto;

import com.haufe.beer.beercatalouge.model.Beer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class BeerDto {


    private Long beerId;

    private String name;

    private String graduation;

    private String type;

    private String description;

    private SimpleManufacturerDto simpleManufacturerDto;

    public static BeerDto from(Beer beer)
    {

        BeerDto beerDto = new BeerDto();
        beerDto.setBeerId(beer.getBeerId());
        beerDto.setName(beer.getName());
        beerDto.setType(beer.getType());
        beerDto.setDescription(beer.getDescription());
        if(Objects.nonNull(beer.getManufacturer())) {
            beerDto.setSimpleManufacturerDto(SimpleManufacturerDto.from(beer.getManufacturer()));
        }

        return beerDto;
    }
}
