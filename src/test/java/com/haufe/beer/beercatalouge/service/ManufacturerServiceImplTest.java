package com.haufe.beer.beercatalouge.service;

import com.haufe.beer.beercatalouge.api.BeerService;
import com.haufe.beer.beercatalouge.api.ManufacturerService;
import com.haufe.beer.beercatalouge.exceptionhandling.BeerAlreadyAddedException;
import com.haufe.beer.beercatalouge.exceptionhandling.BeerCatalogueGenericException;
import com.haufe.beer.beercatalouge.exceptionhandling.ManufacturerNotFoundException;
import com.haufe.beer.beercatalouge.model.Manufacturer;
import com.haufe.beer.beercatalouge.repository.BeerRepository;
import com.haufe.beer.beercatalouge.repository.ManufacturerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.Optional;

import static com.haufe.beer.beercatalouge.utils.BeerTestUtils.beer;
import static com.haufe.beer.beercatalouge.utils.BeerTestUtils.beerToBeAdded;
import static com.haufe.beer.beercatalouge.utils.ManufacturerTestUtils.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ManufacturerServiceImplTest {

    private ManufacturerService manufacturerService;

    @Mock
    private ManufacturerRepository manufacturerRepositoryMock;

    @BeforeEach
    void setManufacturerService()
    {
        this.manufacturerService = new ManufacturerServiceImpl(manufacturerRepositoryMock);
    }

    @Test
    void shouldCreateManufacturer_whenAddManufacturer()
    {
        when(manufacturerRepositoryMock.save(manufacturer)).thenReturn(manufacturer);
        var actualManufacturer = manufacturerService.addManufacturer(manufacturer);
        assertEquals(manufacturer,actualManufacturer);
    }

    @Test
    void shouldReturnManufacturerList_whenGetAllManufacturers()
    {
        when(manufacturerRepositoryMock.findAll(testPage)).thenReturn( manufacturersListExpected);
        var actualManufacturerList = manufacturerService.getAllManufacturers(0,10);
        assertEquals(1,actualManufacturerList.size());
    }

    @Test
    void shouldThrowBeerCatalogueGenericException_whenNoDataFound_forGetAllManufacturers()
    {
        when(manufacturerRepositoryMock.findAll(testPage)).thenReturn( manufacturersListEmpty);
        assertThatThrownBy(() -> manufacturerService.getAllManufacturers(0,10))
                .isInstanceOf(ManufacturerNotFoundException.class);
    }

    @Test
    void shouldReturnManufacturer_whenGetManufacturerById()
    {
        when(manufacturerRepositoryMock.findById(1)).thenReturn(Optional.ofNullable(manufacturer));
        var manufacturerActual = manufacturerService.getManufacturer(1);
        assertEquals(manufacturer,manufacturerActual);
    }

    @Test
    void shouldThrowBeerCatalogueGenericException_whenNoDataFound_forGetManufacturer()
    {
        when(manufacturerRepositoryMock.findById(1)).thenReturn(Optional.ofNullable(null));
        assertThatThrownBy(() -> manufacturerService.getManufacturer(1))
                .isInstanceOf(ManufacturerNotFoundException.class);
    }

    @Test
    void shouldReturnDeleted_whenDeleteManufacturer()
    {
        when(manufacturerRepositoryMock.findById(1)).thenReturn(Optional.ofNullable(manufacturer));
        var deleteMessage = manufacturerService.deleteManufacturer(1);
        assertEquals("Deleted", deleteMessage);
    }

    @Test
    void shouldReturnUpdatedManufacturer_whenUpdateManufacturer()
    {
        when(manufacturerRepositoryMock.findById(1)).thenReturn(Optional.ofNullable(manufacturer));
        when(manufacturerRepositoryMock.save(manufacturer)).thenReturn((manufacturer));
        var updatedManufacturer = manufacturerService.updateManufacturer(1,manufacturer);
        assertEquals(manufacturer, updatedManufacturer);
    }


}
