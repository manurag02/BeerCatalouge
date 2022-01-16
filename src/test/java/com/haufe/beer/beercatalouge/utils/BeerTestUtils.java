package com.haufe.beer.beercatalouge.utils;

import com.haufe.beer.beercatalouge.dto.BeerDto;
import com.haufe.beer.beercatalouge.model.Beer;
import com.haufe.beer.beercatalouge.model.Manufacturer;
import lombok.experimental.UtilityClass;

import static com.haufe.beer.beercatalouge.utils.ManufacturerTestUtils.manufacturer;

@UtilityClass
public class BeerTestUtils {

    public static Beer beer = new Beer();
    public static BeerDto beerDto = new BeerDto();

     static {
         beer.setBeerId(1l);
         beer.setName("KingFisher");
         beer.setType("blonde");
         beer.setDescription("7.5% alc");
         beer.setGraduation(" Bottled");
         beer.setManufacturer(manufacturer);
     }

}
