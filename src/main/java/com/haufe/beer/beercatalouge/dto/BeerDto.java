package com.haufe.beer.beercatalouge.dto;

import com.haufe.beer.beercatalouge.model.Beer;
import com.haufe.beer.beercatalouge.model.Manufacturer;
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


    private Integer id;

    private String name;

    private String graduation;

    private String type;

    private String description;

    private SimpleManufacturerDto manufacturer;

    public static BeerDto from(Beer beer)
    {

        BeerDto beerDto = new BeerDto();
        beerDto.setId(beer.getId());
        beerDto.setName(beer.getName());
        beerDto.setType(beer.getType());
        beerDto.setDescription(beer.getDescription());
        if(Objects.nonNull(beer.getManufacturer())) {
            beerDto.setManufacturer(SimpleManufacturerDto.from(beer.getManufacturer()));
        }

        return beerDto;
    }
}
