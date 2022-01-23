package com.haufe.beer.beercatalouge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.haufe.beer.beercatalouge.dto.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
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
//    @SequenceGenerator(name="BEER_ID", sequenceName = "BEER_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
//    @ManyToOne(fetch=FetchType.LAZY , cascade=CascadeType.ALL)
    @ManyToOne(fetch=FetchType.LAZY , optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "manufacturer_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @JsonBackReference
    private Manufacturer manufacturer;


    public static Beer from(BeerDto beerDto)
    {
        Beer beer = new Beer();
        beer.setId(beerDto.getId());
        beer.setName(beerDto.getName());
        beer.setDescription(beerDto.getDescription());
        beer.setType(beerDto.getType());

        if(Objects.nonNull(beerDto.getManufacturer()))
        {
//            beer.setManufacturer(beerDto.getManufacturer());
                        beer.setManufacturer(
                    Manufacturer.builder()
                    .id(beerDto.getManufacturer().getId())
                    .name(beerDto.getManufacturer().getName())
                    .nationality(beerDto.getManufacturer().getNationality())
            .build());
        }

        return beer;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId(Integer id) {
        return this.id;
    }


}
