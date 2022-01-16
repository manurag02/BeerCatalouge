//package com.haufe.beer.beercatalouge.service;
//
//import com.haufe.beer.beercatalouge.api.ManufacturerService;
//import com.haufe.beer.beercatalouge.repository.ManufacturerRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static com.haufe.beer.beercatalouge.utils.ManufacturerTestUtils.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class ManufacturerServiceImplTest {
//
//    private ManufacturerService manufacturerService;
//
//    @Mock
//    private ManufacturerRepository manufacturerRepositoryMock;
//
//    @BeforeEach
//    void setManufacturerService()
//    {
//        this.manufacturerService = new ManufacturerServiceImpl(manufacturerRepositoryMock);
//    }
//
//    @Test
//    void shouldCreateManufacturer_whenAddManufacturer()
//    {
//        when(manufacturerRepositoryMock.save(manufacturer)).thenReturn(manufacturer);
//        var actualManufacturer = manufacturerService.addManufacturer(manufacturer);
//        assertEquals(manufacturer,actualManufacturer);
//    }
//
//    @Test
//    void shouldReturnManufacturerList_whenGetAllManufacturer()
//    {
//        when(manufacturerRepositoryMock.findAll(testPage)).thenReturn(manufacturersListExpected);
//        var actualManufacturerList = manufacturerService.getAllManufacturers(0,10);
//        assertEquals(manufacturersListExpected.getSize(),actualManufacturerList.size());
//    }
//
//}
