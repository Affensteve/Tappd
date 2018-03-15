package de.tappd.Tappd.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.tappd.Tappd.model.Beer;
import de.tappd.Tappd.model.BeerStyle;
import de.tappd.Tappd.model.Brewery;
import de.tappd.Tappd.repo.BeerRepository;
import de.tappd.Tappd.repo.BeerStyleRepository;
import de.tappd.Tappd.repo.BreweryNameRepository;

@Component
public class BeerProcess {

	private final BeerRepository repo;
	private final BeerStyleRepository beerStyleRepository;
	private final BreweryNameRepository breweryRepository;

	@Autowired
	public BeerProcess(BeerRepository repo, BeerStyleRepository beerStyleRepository,
			BreweryNameRepository breweryRepository) {
		this.repo = repo;
		this.beerStyleRepository = beerStyleRepository;
		this.breweryRepository = breweryRepository;

	}

	@Transactional
	public Beer saveBeer(Beer beer) {
		if (beerStyleRepository.findByBeerStylesStartsWithIgnoreCase(beer.getBeerStyle().getBeerStyles()) != null) {
			beerStyleRepository.save(new BeerStyle(beer.getBeerStyle().getBeerStyles()));
		} else {
			beerStyleRepository.save(beer.getBeerStyle());
		}
		if (breweryRepository.findByBreweryNameStartsWithIgnoreCase(beer.getBreweryName().getBreweryName()) != null) {
			breweryRepository.save(new Brewery(beer.getBreweryName().getBreweryName()));
		} else {
			breweryRepository.save(beer.getBreweryName());
		}
		return repo.save(beer);
	}
}
