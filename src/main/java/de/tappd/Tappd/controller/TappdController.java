package de.tappd.Tappd.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

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
				breweryNameRepository.findByBreweryNameStartsWithIgnoreCase("Buddelship Brauerei").get(0), "Bernstein",
				7.9, 0.0, 4.75));

		// save a list of Customers
		beerRepository.save(Arrays.asList( //
				new Beer("Triticum Wormatia",
						beerStyleRepository.findByBeerStylesStartsWithIgnoreCase("Hefeweizen").get(0),
						breweryNameRepository.findByBreweryNameStartsWithIgnoreCase("Barnstedter Brau-Kunz").get(0),
						"Hell Gelb", 5.6, 17.0, 3.75), //
				new Beer("IPA Simcoe",
						beerStyleRepository.findByBeerStylesStartsWithIgnoreCase("IPA - American").get(0),
						breweryNameRepository.findByBreweryNameStartsWithIgnoreCase("Barnstedter Brau-Kunz").get(0),
						"Bernstein", 7.0, 50.0, 4.5), //
				new Beer("Noa Pecan Mud Cake Stout (Bourbon Barrel Aged)",
						beerStyleRepository.findByBeerStylesStartsWithIgnoreCase("Stout - Imperial / Double").get(0),
						breweryNameRepository.findByBreweryNameStartsWithIgnoreCase("Omnipollo").get(0), "Tiefschwarz",
						11.0, 60.0, 5.0), //
				new Beer("SLYRS",
						beerStyleRepository.findByBeerStylesStartsWithIgnoreCase("Stout - Imperial / Double").get(0),
						breweryNameRepository.findByBreweryNameStartsWithIgnoreCase("Hoppebräu").get(0), "Tiefschwarz",
						11.1, 56.0, 5.0)));

		return "Done";
	}

	@RequestMapping("/style")
	public String style() {
		List<BeerStyles> beerStyleList = Arrays.asList(BeerStyles.values());
		for (int i = 0; i < beerStyleList.size(); i++) {
			beerStyleRepository.save(new BeerStyle(beerStyleList.get(i).displayName()));
		}

		return "Style done";
	}

	@RequestMapping("/brewery")
	public String brewery() {
		breweryNameRepository.save(new Brewery("Barnstedter Brau-Kunz"));
		breweryNameRepository.save(new Brewery("Buddelship Brauerei"));
		breweryNameRepository.save(new Brewery("Omnipollo"));
		breweryNameRepository.save(new Brewery("Hoppebräu"));
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