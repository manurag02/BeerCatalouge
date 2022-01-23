package com.haufe.beer.beercatalouge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haufe.beer.beercatalouge.api.ManufacturerService;
import com.haufe.beer.beercatalouge.dto.ManufacturerDto;
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

import static com.haufe.beer.beercatalouge.utils.ManufacturerTestUtils.manufacturer;
import static com.haufe.beer.beercatalouge.utils.ManufacturerTestUtils.manufacturerDto;
import static org.mockito.Mockito.when;
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
        this.manufacturerController = new ManufacturerController(manufacturerServiceMock, objectMapper);
        this.mockMvc = MockMvcBuilders.standaloneSetup(manufacturerController).build();
    }

    @Test
    void shouldReturn200Ok_whenGetAllManufacturers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/manufacturers"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn200Ok_whenGetManufacturer() throws Exception {
        when(manufacturerServiceMock.getManufacturer(1)).thenReturn(manufacturer);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/manufacturers/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn201Created_whenAddManufacturer() throws Exception {
        when(manufacturerServiceMock.addManufacturer(Manufacturer.from(manufacturerDto))).thenReturn(manufacturer);
        try (MockedStatic<ManufacturerDto> utilities = Mockito.mockStatic(ManufacturerDto.class)) {
            utilities.when(() -> ManufacturerDto.from(manufacturer)).thenReturn(manufacturerDto);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/manufacturers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(manufacturerDto)))
                    .andExpect(status().is2xxSuccessful());
        }
    }

    @Test
    void shouldReturn200Ok_whenUpdateManufacturer() throws Exception {
        when(manufacturerServiceMock.updateManufacturer(1,Manufacturer.from(manufacturerDto))).thenReturn(manufacturer);
        try (MockedStatic<ManufacturerDto> utilities = Mockito.mockStatic(ManufacturerDto.class)) {
            utilities.when(() -> ManufacturerDto.from(manufacturer)).thenReturn(manufacturerDto);
            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/manufacturers/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(manufacturer)))
                    .andExpect(status().is2xxSuccessful());
        }
    }


    @Test
    void shouldReturn200Ok_whenDeleteManufacturer() throws Exception {
        when(manufacturerServiceMock.deleteManufacturer(1)).thenReturn("Deleted");
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/manufacturers/1"))
                .andExpect(status().is2xxSuccessful());

    }
}
