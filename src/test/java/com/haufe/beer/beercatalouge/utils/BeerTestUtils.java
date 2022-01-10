package com.haufe.beer.beercatalouge.utils;

import com.haufe.beer.beercatalouge.model.Beer;
import com.haufe.beer.beercatalouge.model.Manufacturer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BeerTestUtils {

    public static Beer beer = Beer.builder().manufacturer(Manufacturer.builder().id(1l).name("UBGroup").build()).build();

}
