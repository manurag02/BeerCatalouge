package com.haufe.beer.beercatalouge.utils;

import com.haufe.beer.beercatalouge.model.Manufacturer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ManufacturerTestUtils {

    public static Manufacturer manufacturer = Manufacturer.builder().id(1l).name("UBGroup").nationality("Indian").build();
}
