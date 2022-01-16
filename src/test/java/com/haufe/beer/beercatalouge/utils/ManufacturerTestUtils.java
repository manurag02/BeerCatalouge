package com.haufe.beer.beercatalouge.utils;

import com.haufe.beer.beercatalouge.dto.ManufacturerDto;
import com.haufe.beer.beercatalouge.model.Beer;
import com.haufe.beer.beercatalouge.model.Manufacturer;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static com.haufe.beer.beercatalouge.utils.BeerTestUtils.beer;

@UtilityClass
public class ManufacturerTestUtils {

    public static List<Manufacturer> manufacturerList = new ArrayList<>();

    public static List<Beer> beerList = new ArrayList<>();

    public static Manufacturer manufacturer = Manufacturer.builder().id(1l).name("UBGroup").nationality("Indian").beers(Collections.emptyList()).build();

    public static Manufacturer manufacturerWithBeers = Manufacturer.builder().id(1l).name("UBGroup").nationality("Indian").beers(beerList).build();

    public static ManufacturerDto manufacturerDto = ManufacturerDto.from(manufacturer);


    static
    {
        manufacturerList.add(manufacturer);
        beerList.add(beer);
    }

    public static Pageable testPage = PageRequest.of(0,10);

    public static Page<Manufacturer> manufacturersListExpected = new Page<>() {
        @Override
        public int getTotalPages() {
            return 0;
        }

        @Override
        public long getTotalElements() {
            return 0;
        }

        @Override
        public <U> Page<U> map(Function<? super Manufacturer, ? extends U> function) {
            return null;
        }

        @Override
        public int getNumber() {
            return 0;
        }

        @Override
        public int getSize() {
            return 0;
        }

        @Override
        public int getNumberOfElements() {
            return 0;
        }

        @Override
        public List<Manufacturer> getContent() {
           return manufacturerList;
        }

        @Override
        public boolean hasContent() {
            return false;
        }

        @Override
        public Sort getSort() {
            return null;
        }

        @Override
        public boolean isFirst() {
            return false;
        }

        @Override
        public boolean isLast() {
            return false;
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public Pageable nextPageable() {
            return null;
        }

        @Override
        public Pageable previousPageable() {
            return null;
        }

        @Override
        public Iterator<Manufacturer> iterator() {
            return null;
        }
    };


    public static Page<Manufacturer> manufacturersListEmpty = new Page<>() {
        @Override
        public int getTotalPages() {
            return 0;
        }

        @Override
        public long getTotalElements() {
            return 0;
        }

        @Override
        public <U> Page<U> map(Function<? super Manufacturer, ? extends U> function) {
            return null;
        }

        @Override
        public int getNumber() {
            return 0;
        }

        @Override
        public int getSize() {
            return 0;
        }

        @Override
        public int getNumberOfElements() {
            return 0;
        }

        @Override
        public List<Manufacturer> getContent() {
            return Collections.emptyList();
        }

        @Override
        public boolean hasContent() {
            return false;
        }

        @Override
        public Sort getSort() {
            return null;
        }

        @Override
        public boolean isFirst() {
            return false;
        }

        @Override
        public boolean isLast() {
            return false;
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public Pageable nextPageable() {
            return null;
        }

        @Override
        public Pageable previousPageable() {
            return null;
        }

        @Override
        public Iterator<Manufacturer> iterator() {
            return null;
        }
    };


}
