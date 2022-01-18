package com.haufe.beer.beercatalouge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.haufe.beer.beercatalouge.dto.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "beers")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"version"})
public class Beer implements Serializable {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The Id of the beer
     */
    @Id
    @SequenceGenerator(name="BEER_ID", sequenceName = "BEER_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BEER_ID")
    private Long beerId;

    @Version
    private Long version;
    /**
     * The name of the beer
     */
    private String name;

    /**
     * Graduation of the beer
     */
    private String graduation;

    /**
     * Type of the beer
     */
    private String type;

    /**
     * Description of the beer
     */
    private String description;

    /**
     * Manufacturer of the beer
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Manufacturer manufacturer;


    public static Beer from(BeerDto beerDto)
    {
        Beer beer = new Beer();
        beer.setBeerId(beerDto.getBeerId());
        beer.setName(beerDto.getName());
        beer.setDescription(beerDto.getDescription());
        beer.setType(beerDto.getType());
        if(Objects.nonNull(beerDto.getManufacturer()))
        {
            beer.setManufacturer(
                    Manufacturer.builder()
                    .id(beerDto.getManufacturer().getId())
                    .name(beerDto.getManufacturer().getName())
                    .nationality(beerDto.getManufacturer().getNationality())
            .build());
        }
        return beer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Beer )) return false;
        return beerId != null && beerId.equals(((Beer) o).getBeerId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
