package com.haufe.beer.beercatalouge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Beer implements Serializable {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The Id of the beer
     */
    private Long id;

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
    @ManyToOne
    private Manufacturer manufacturer;

}
