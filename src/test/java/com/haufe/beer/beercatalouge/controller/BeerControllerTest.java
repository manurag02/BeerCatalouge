package com.haufe.beer.beercatalouge.controller;

import com.haufe.beer.beercatalouge.api.BeerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BeerControllerTest {

    @Mock
    private BeerService beerServiceMock;

    private MockMvc mockMvc;

    private BeerController beerController;

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
    void shouldReturn200Ok_whenGetAllBeersByManufacturerName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beers/UBGroup/manufacturer"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn200Ok_whenGetAllBeersByType() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beers/blonde/type"))
                .andExpect(status().isOk());
    }


    @Test
    void shouldReturn200Ok_whenBeerByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beers/kingfisher/name"))
                .andExpect(status().isOk());
    }


}
