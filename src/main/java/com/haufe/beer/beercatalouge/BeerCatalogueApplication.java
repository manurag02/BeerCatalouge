package com.haufe.beer.beercatalouge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class BeerCatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeerCatalogueApplication.class, args);
	}

}
