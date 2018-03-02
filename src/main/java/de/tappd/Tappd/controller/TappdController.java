package de.tappd.Tappd.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.tappd.Tappd.model.Beer;
import de.tappd.Tappd.model.BeerStyle;
import de.tappd.Tappd.model.Brewery;
import de.tappd.Tappd.model.style.BeerStyles;
import de.tappd.Tappd.repo.BeerRepository;
import de.tappd.Tappd.repo.BeerStyleRepository;
import de.tappd.Tappd.repo.BreweryNameRepository;

@RestController
@RequestMapping("rest")
public class TappdController {
	@Autowired
	BeerRepository beerRepository;

	@Autowired
	BeerStyleRepository beerStyleRepository;

	@Autowired
	BreweryNameRepository breweryNameRepository;

	@RequestMapping("/beer")
	public String process() {
		// save a single Beer
		// Beer(String name, BeerStyle beerStyle, BreweryName breweryName, String
		// color, Double abv, Double ibu,Double rating)

		beerRepository.save(new Beer("Mr. O",
				beerStyleRepository.findByBeerStylesStartsWithIgnoreCase("IPA - Imperial / Double").get(0),
				breweryNameRepository.findByBreweryNameStartsWithIgnoreCase("Buddelship Brauerei").get(0), "Bernstein", 7.9, 0.0, 4.75));

		// save a list of Customers
		// beerRepository.save(Arrays.asList( //
		// new Beer("Triticum Wormatia", new
		// BeerStyle(BeerStyles.HEFEWEIZEN.toString()),
		// new Brewery("Barnstedter Brau-Kunz"), "Hell Gelb", 5.6, 17.0, 3.75), //
		// new Beer("IPA Simcoe", new BeerStyle(BeerStyles.IPA_AMERICAN.toString()),
		// new Brewery("Barnstedter Brau-Kunz"), "Bernstein", 7.0, 50.0, 4.5), //
		// new Beer("Noa Pecan Mud Cake Stout (Bourbon Barrel Aged)",
		// new BeerStyle(BeerStyles.STOUT_IMPERIAL_DOUBLE.toString()), new
		// Brewery("Omnipollo"),
		// "Tiefschwarz", 11.0, 60.0, 5.0), //
		// new Beer("SLYRS", new
		// BeerStyle(BeerStyles.STOUT_AMERICAN_IMPERIAL_DOUBLE.toString()),
		// new Brewery("Hoppebraeu"), "Tiefschwarz", 11.1, 56.0, 5.0)));

		return "Done";
	}

	@RequestMapping("/style")
	public String style() {
		beerStyleRepository.save(new BeerStyle(BeerStyles.IPA_IMPERIAL_DOUBLE.toString()));
		return "Style done";
	}

	@RequestMapping("/brewery")
	public String brewery() {
		breweryNameRepository.save(new Brewery("Buddelship Brauerei"));
		return "Brewery done";
	}

	@RequestMapping("/findall")
	public String findAll() {
		String result = "";

		for (Beer cust : beerRepository.findAll()) {
			result += cust.toString() + "<br>";
		}

		return result;
	}

	@RequestMapping("/findbyid")
	public String findById(@RequestParam("id") long id) {
		String result = "";
		result = beerRepository.findOne(id).toString();
		return result;
	}

	@RequestMapping("/findbyame")
	public String fetchDataByName(@RequestParam("name") String name) {
		String result = "";

		for (Beer cust : beerRepository.findByNameStartsWithIgnoreCase(name)) {
			result += cust.toString() + "<br>";
		}

		return result;
	}

}