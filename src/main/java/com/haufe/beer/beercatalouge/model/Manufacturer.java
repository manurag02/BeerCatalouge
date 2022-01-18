package com.haufe.beer.beercatalouge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.haufe.beer.beercatalouge.dto.BeerDto;
import com.haufe.beer.beercatalouge.dto.ManufacturerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "manufacturer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties({"dateCreated", "dateUpdated"})
public class Manufacturer implements Serializable {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The Id of the manufacturer
     */
    @Id
    @SequenceGenerator(name = "MANUFACTURER_ID", sequenceName = "MANUFACTURER_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MANUFACTURER_ID")
    @Column(name = "MANUFACTURER_ID")
    private Long id;

    @Version
    @JsonIgnore
    private Long version;

    /**
     * The name of the manufacturer
     */
    @Column(unique = true)
    private String name;

    /**
     * The nationality of the manufacturer
     */
    private String nationality;

    /**
     * The beers by manufacturer
     */
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MANUFACTURER_ID")
    private List<Beer> beers = new ArrayList<>();

    /**
     * The Date created.
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    /**
     * The Date updated.
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateUpdated = ZonedDateTime.now();


    public static Manufacturer from(ManufacturerDto manufacturerDto)
    {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(manufacturerDto.getId());
        manufacturer.setName(manufacturerDto.getName());
        manufacturer.setNationality(manufacturerDto.getNationality());
        if(Objects.nonNull(manufacturerDto.getBeersDto()))
        {
            manufacturer.setBeers(manufacturerDto.getBeersDto().stream().map(Beer::from).collect(Collectors.toList()));
        }
       return manufacturer;
    }

}
