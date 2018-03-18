package de.tappd.Tappd.process;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		List<BeerStyle> foundBeerStyles = beerStyleRepository.findByBeerStylesStartsWithIgnoreCase(beer.getBeerStyle().getBeerStyles());
		if (foundBeerStyles.isEmpty()) {
			 beerStyleRepository.save(beer.getBeerStyle());
		} else {
			//attach Beerstyle
			BeerStyle attachedBeerStyle = beerStyleRepository.getOne(beer.getBeerStyle().getId());
			beer.setBeerStyle(attachedBeerStyle);
		}
		List<Brewery> foundBreweries = breweryRepository.findByBreweryNameStartsWithIgnoreCase(beer.getBreweryName().getBreweryName());
		if (foundBreweries.isEmpty()) {
			breweryRepository.save(beer.getBreweryName());
		} else {
			//attach Beerstyle
			Brewery attachedBrewery = breweryRepository.getOne(beer.getBreweryName().getId());
			beer.setBreweryName(attachedBrewery);
		}
		return repo.save(beer);
	}
	
}
