package com.haufe.beer.beercatalouge.utils;

import com.haufe.beer.beercatalouge.dto.BeerDto;
import com.haufe.beer.beercatalouge.model.Beer;
import com.haufe.beer.beercatalouge.model.Manufacturer;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static com.haufe.beer.beercatalouge.utils.ManufacturerTestUtils.beerList;
import static com.haufe.beer.beercatalouge.utils.ManufacturerTestUtils.manufacturer;

@UtilityClass
public class BeerTestUtils {

    public static Beer beer = new Beer();
    public static Beer beerToBeAdded = new Beer();
    public static BeerDto beerDto = new BeerDto();

     static {
         beer.setBeerId(1l);
         beer.setName("KingFisher");
         beer.setType("blonde");
         beer.setDescription("7.5% alc");
         beer.setGraduation(" Bottled");
         beer.setManufacturer(manufacturer);

         beerToBeAdded.setBeerId(1l);
         beerToBeAdded.setName("KingFisher");
         beerToBeAdded.setType("blonde");
         beerToBeAdded.setDescription("7.5% alc");
         beerToBeAdded.setGraduation(" Bottled");
     }

     public static Page<Beer> beerPage = new Page<>()
     {

         @Override
         public Iterator<Beer> iterator() {
             return null;
         }

         @Override
         public int getTotalPages() {
             return 0;
         }

         @Override
         public long getTotalElements() {
             return 0;
         }

         @Override
         public <U> Page<U> map(Function<? super Beer, ? extends U> function) {
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
         public List<Beer> getContent() {
             return beerList;
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
     };

    public static Page<Beer> beerPageEmpty = new Page<>()
    {

        @Override
        public Iterator<Beer> iterator() {
            return null;
        }

        @Override
        public int getTotalPages() {
            return 0;
        }

        @Override
        public long getTotalElements() {
            return 0;
        }

        @Override
        public <U> Page<U> map(Function<? super Beer, ? extends U> function) {
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
        public List<Beer> getContent() {
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
    };

}
