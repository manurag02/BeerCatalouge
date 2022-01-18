package com.haufe.beer.beercatalouge.service;

import com.haufe.beer.beercatalouge.api.BeerService;
import com.haufe.beer.beercatalouge.exceptionhandling.BeerCatalogueGenericException;
import com.haufe.beer.beercatalouge.exceptionhandling.BeerNotFoundException;
import com.haufe.beer.beercatalouge.repository.BeerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.haufe.beer.beercatalouge.utils.BeerTestUtils.*;
import static com.haufe.beer.beercatalouge.utils.ManufacturerTestUtils.testPage;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BeerServiceImplTest {

    @Mock
    private BeerRepository beerRepositoryMock;

    private BeerService beerServiceImplMock;

    @BeforeEach
    void setBeerRepositoryMock()
    {
        this.beerServiceImplMock = new BeerServiceImpl(beerRepositoryMock);
    }

    @Test
    void shouldGetAllBeers()
    {
        when(beerRepositoryMock.findAll(testPage)).thenReturn(beerPage);
        var actualBeerList = beerServiceImplMock.getAllBeers(0,10);
        assertEquals(1,actualBeerList.size());
    }

    @Test
    void shouldThrowBeerCatalogueGenericException_whenNoDataFound_forGetAllBeers()
    {
        when(beerRepositoryMock.findAll(testPage)).thenReturn( beerPageEmpty);
        assertThatThrownBy(() -> beerServiceImplMock.getAllBeers(0,10))
                .isInstanceOf(BeerNotFoundException.class);
    }

    @Test
    void shouldReturnBeer_whenGetBeerById()
    {
        when(beerRepositoryMock.findById(1l)).thenReturn(Optional.ofNullable(beer));
        var beerActual = beerServiceImplMock.getBeer(1l);
        assertEquals(beer,beerActual);
    }


    @Test
    void shouldThrowBeerNotFoundException_whenNoDataFound_forGetBeer()
    {
        when(beerRepositoryMock.findById(1l)).thenReturn(Optional.ofNullable(null));
        assertThatThrownBy(() -> beerServiceImplMock.getBeer(1l))
                .isInstanceOf(BeerNotFoundException.class);
    }


    @Test
    void shouldGetAllBeers_whenGetBeersByBeerType()
    {
        when(beerRepositoryMock.getBeersByType("blonde",testPage)).thenReturn(beerPage);
        var actualBeerList = beerServiceImplMock.getAllBeersByType("blonde",0,10);
        assertEquals(1,actualBeerList.size());
    }

    @Test
    void shouldThrowBeerCatalogueGenericException_whenGetBeersByBeerType()
    {
        when(beerRepositoryMock.getBeersByType("blonde",testPage)).thenReturn(beerPageEmpty);
        assertThatThrownBy(() -> beerServiceImplMock.getAllBeersByType("blonde",0,10))
                .isInstanceOf(BeerNotFoundException.class);
    }


}
