package com.haufe.beer.beercatalouge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haufe.beer.beercatalouge.api.BeerService;
import com.haufe.beer.beercatalouge.dto.BeerDto;
import com.haufe.beer.beercatalouge.dto.ManufacturerDto;
import com.haufe.beer.beercatalouge.model.Beer;
import com.haufe.beer.beercatalouge.model.Manufacturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.haufe.beer.beercatalouge.utils.BeerTestUtils.beer;
import static com.haufe.beer.beercatalouge.utils.BeerTestUtils.beerDto;
import static com.haufe.beer.beercatalouge.utils.ManufacturerTestUtils.manufacturer;
import static com.haufe.beer.beercatalouge.utils.ManufacturerTestUtils.manufacturerDto;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BeerControllerTest {

    @Mock
    private BeerService beerServiceMock;

    private MockMvc mockMvc;

    private BeerController beerController;

    @InjectMocks
    private ObjectMapper objectMapper;

    @BeforeEach
    void setBeerController()
    {
        this.beerController = new BeerController(beerServiceMock);
        this.mockMvc = MockMvcBuilders.standaloneSetup(beerController).build();
    }

    @Test
    void shouldReturn200Ok_whenGetAllBeers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beers"))
                .andExpect(status().isOk());
    }


    @Test
    void shouldReturn200Ok_whenGetAllBeersByType() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beers/blonde/type"))
                .andExpect(status().isOk());
    }


    @Test
    void shouldReturn200Ok_whenGetBeerById() throws Exception {
        when(beerServiceMock.getBeer(1)).thenReturn(beer);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beers/"+1l))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn201Created_whenAddBeer() throws Exception {
        when(beerServiceMock.addBeer(Beer.from(beerDto))).thenReturn(beer);
        try (MockedStatic<BeerDto> utilities = Mockito.mockStatic(BeerDto.class)) {
            utilities.when(() -> BeerDto.from(beer)).thenReturn(beerDto);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(beerDto)))
                    .andExpect(status().is2xxSuccessful());
        }
    }

    @Test
    void shouldReturn200Ok_whenUpdateBeer() throws Exception {
        when(beerServiceMock.updateBeer(1,Beer.from(beerDto))).thenReturn(beer);
        try (MockedStatic<BeerDto> utilities = Mockito.mockStatic(BeerDto.class)) {
            utilities.when(() -> BeerDto.from(beer)).thenReturn(beerDto);
            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/beers/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(beerDto)))
                    .andExpect(status().is2xxSuccessful());
        }
    }

    @Test
    void shouldReturn200Ok_whenDeleteBeer() throws Exception {
        when(beerServiceMock.deleteBeer(1)).thenReturn("Deleted");
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/beers/1"))
                    .andExpect(status().is2xxSuccessful());

    }



}
