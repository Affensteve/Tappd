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
public class TappdApplication implements CommandLineRunner {

	@Autowired
	BeerRepository repository;

	@Autowired
	BeerStyleRepository beerStyleRepository;

	@Autowired
	BreweryNameRepository breweryNameRepository;

	public static void main(String[] args) {
		SpringApplication.run(TappdApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// clear all record if existed before do the tutorial with new data
		repository.deleteAll();
		beerStyleRepository.deleteAll();
		breweryNameRepository.deleteAll();
	}
}
