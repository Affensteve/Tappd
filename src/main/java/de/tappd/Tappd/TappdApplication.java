package de.tappd.Tappd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import de.tappd.Tappd.repo.BeerRepository;
import de.tappd.Tappd.repo.BeerStyleRepository;
import de.tappd.Tappd.repo.BreweryNameRepository;

@SpringBootApplication
@ComponentScan("de.tappd.Tappd")
public class TappdApplication {

	@Autowired
	BeerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(TappdApplication.class, args);
	}

}
