package com.haufe.beer.beercatalouge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haufe.beer.beercatalouge.api.ManufacturerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.haufe.beer.beercatalouge.utils.BeerTestUtils.beer;
import static com.haufe.beer.beercatalouge.utils.ManufacturerTestUtils.manufacturer;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ManufacturerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ManufacturerService manufacturerServiceMock;

    @InjectMocks
    private ObjectMapper objectMapper;

    private ManufacturerController manufacturerController;

    @BeforeEach
    public void setMockMvc()
    {
        this.manufacturerController = new ManufacturerController(manufacturerServiceMock);
        this.mockMvc = MockMvcBuilders.standaloneSetup(manufacturerController).build();
    }

    @Test
    void shouldReturn200Ok_whenGetAllManufacturers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/manufacturers"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn200Ok_whenGetManufacturerByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/manufacturers/UBGroup"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn201Created_whenAddManufacturer() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/manufacturers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(manufacturer)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void shouldReturn200Ok_whenUpdateManufacturer() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/manufacturers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(manufacturer)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void shouldReturn200Ok_whenUpdateManufacturerBeers() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/manufacturers/1/beers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beer)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void shouldReturn200Ok_whenDeleteManufacturer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/manufacturers/1"))
                .andExpect(status().is2xxSuccessful());

    }
}
