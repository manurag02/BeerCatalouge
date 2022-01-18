package com.haufe.beer.beercatalouge.repository;

import com.haufe.beer.beercatalouge.model.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {

    @Query("SELECT b FROM Beer b where b.manufacturer.id = ?1")
    Page<Beer> getBeersByManufactureId(Long id, Pageable page);

    @Query("SELECT b FROM Beer b where b.type = ?1")
    Page<Beer> getBeersByType(String type, Pageable page);

}
