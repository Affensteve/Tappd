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
		return beerStyleList.size() + " Beer-Styles imported";
	}

	@RequestMapping("/brewery")
	public String brewery() {
		List<String> breweryNames = Arrays.asList("Abbaye de Leffe", "Abbaye Notre-Dame de Saint-Rémy",
				"ABK ktienbrauerei Kaufbeuren)", "Adler Brauereigasthof Moosbeuren", "Ale-Mania", "Alefried",
				"Allgäuer Brauhaus", "Anadolu Efes", "Anchor Brewing Company", "AND UNION", "Angry Bullets Brewery",
				"Arrogant Brewing", "Augustiner-Bräu München", "Austmann", "Ayinger Privatbrauerei",
				"Badische Staatsbrauerei Rothaus", "Barfüßer Hausbrauerei Nürnberg", "Barnstedter Brau-Kunz",
				"Batemans", "Bayerische Staatsbrauerei Weihenstephan", "Bayreuther Bierbrauerei", "Beavertown",
				"Beer Brauerei", "Beer of the Gods - Wacken Brauerei", "Belhaven Brewery", "Berliner Berg Brauerei",
				"Berliner Bier Barone", "Berliner-Kindl-Schultheiss-Brauerei", "Bierbrouwerij De Koningshoeven",
				"Bières de Chimay", "Bierfabrik", "Biermanufaktur ENGEL", "Bionade", "Birra Forst", "Birra Moretti",
				"Birra Peroni", "Birra Toccalmatto", "Bitburger Braugruppe", "Böhmisch Brauhaus Großröhrsdorf",
				"Boon Rawd Brewery", "Boston Beer Company", "Boulevard Brewing Co.", "Brasserie d'Orval",
				"Brasserie Dupont", "Brau Union Österreich", "Brauerei Beck", "Brauerei Bosch", "Brauerei Braunschweig",
				"Brauerei Diebels", "Brauerei Gebr. Maisel", "Brauerei Göss", "Brauerei Gutmann", "Brauerei Lemke",
				"Brauerei Pinkus Müller", "Brauerei Ried", "Brauerei Rittmayer Hallerndorf",
				"Brauerei Schloss Starkenberg", "Brauerei Veltins", "Brauerei Westheim", "Brauerei Zirndorf",
				"Brauerei Zwönitz", "Brauhaus Bevog", "Brauhaus Riegele", "Brauhaus Thombansen",
				"Braukunst Gebrüder Wiestner", "Bremer Braumanufaktur", "Brew Age", "BrewDog", "BrewFist", "BRLO",
				"Brooklyn Brewery", "Brouwerij Alken-Maes", "Brouwerij Boon", "Brouwerij De Block",
				"Brouwerij De Molen", "Brouwerij der Trappisten van Westmalle", "Brouwerij St. Bernardus",
				"Brouwerij Van Honsebrouck", "Browar Golem", "Bucher Bräu", "Buddelship Brauerei", "Budějovický Budvar",
				"Burgbrauerei Hessberg", "Butcher’s Tears", "Camba Bavaria", "Cambrew", "Carlsberg Group",
				"Carlsberg UK", "Cerveceria Bucanero", "Cervecería y Maltería Quilmes", "Coisbo Beer",
				"Compañía Cervecera de Canarias", "CREW Republic", "Dampfbierbrauerei Zwiesel", "Darguner Brauerei",
				"De Halve Maan", "Der Hirschbräu Höss", "Desnoes & Geddes", "Desperados NL", "Distelhäuser Brauerei",
				"Dithmarscher Privatbrauerei", "Duckstein Brewery (Australia)", "Edeka", "Eder & Heylands Brauerei",
				"Einbecker Brauhaus", "Einstök Ölgerð", "Erdinger Weissbräu", "Finne Brauerei",
				"Firestone Walker Brewing Company", "Flensburger Brauerei Emil Petersen", "Flötzinger Bräu",
				"Flying Dog Brewery", "Founders Brewing Co.", "Frankfurter Brauhaus", "Freie Brau Union Bremen",
				"Friesisches Brauhaus zu Jever", "FUCKING HELL GmbH & Co. Handels KG", "Fuller, Smith & Turner",
				"Gasthausbrauerei & Brennerei Nolte", "Gilde Brauerei", "Glückauf Biere", "Gotlands Bryggeri",
				"Greene King", "Grönwohlder Hausbrauerei", "Grüner Bier", "Grupo Mahou-San Miguel", "Grupo Modelo",
				"Guinness", "Hacker-Pschorr", "Häffner Bräu - Hopfenstopfer", "Hanscraft & Co.",
				"Hanseatische Brauerei Rostock", "Hartwall", "Hasseröder Brauerei", "Heidenpeters", "Heineken",
				"Heineken Asia Pacific", "Herrnbräu", "Herzoglich Bayerisches Brauhaus Tegernsee",
				"Himburgs BrauKunstKeller", "HiteJinro", "HobbyBrau Hamburg", "Hofbrauhaus Wolters",
				"Hohenthanner Schlossbrauerei", "Holsten-Brauerei", "Hopfmeister", "Hoppebräu", "Karlsberg Brauerei",
				"Karma", "Kauzen-Bräu", "Kehrwieder Kreativbrauerei", "Kilimanjaro Beer Works",
				"Kirner Privatbrauerei Ph. und C. Andres", "Kiuchi Brewery", "Klosterbrauerei Andechs",
				"Klosterbrauerei Machern", "Klosterbrauerei Neuzelle", "Klosterbrauerei Weissenohe",
				"Klosterbrauerei Weltenburg", "Klosterbryggeriet", "Kona Brewing Company", "König Brauerei",
				"König Ludwig Schlossbrauerei Kaltenberg", "Köstritzer Schwarzbierbrauerei", "Krombacher Gruppe",
				"Kronenbourg", "Krönleins Bryggeri", "KUEHN KUNZ ROSEN", "Kulmbacher Brauerei", "Labieratorium",
				"Landgang Brauerei", "Lao Brewery", "Lech Browary Wielkopolski (Kompania Piwowarska)", "LERVIG",
				"Lidl Deutschland / Germany", "Litovel", "Löwenbrauerei Passau", "Magic Rock Brewing",
				"Mälzer Brau- und Tafelhaus", "Mashsee Brauerei", "MBG International Premium Brands", "McGargles",
				"Meckatzer Löwenbräu", "Mikkeller", "Miller Brewing Company", "Molson (Coors K)",
				"Mongozo BV Netherlands", "Moosehead Breweries Limited", "Nebraska Brewing Company",
				"Neumarkter Glossnerbräu", "Neumarkter Lammsbräu", "Next Level Brewing",
				"O'Hara's Brewery arlow (Brewing Company)", "Oettinger Brauerei", "Olympic Brewery", "Omnipollo",
				"Ottakringer Brauerei", "Paderborner Brauerei", "Park & Bellheimer Brauereien", "Paulaner Brauerei",
				"PFUNGSTÄDTER Privatbrauerei", "pibe's Brauerei", "Pivovar Kout na Šumavě", "Pivovary Staropramen",
				"Pizza Port Brewing Co.", "Plzeňský Prazdroj", "Põhjala", "Poppels Bryggeri", "Pracownia Piwa",
				"Premium Beers From Spain", "PREMIUM by Uwe Lübbermann", "Privat-Brauerei Schmucker",
				"Privat-Brauerei Zötler", "Privatbrauerei Eibau", "Privatbrauerei Eichbaum",
				"Privatbrauerei Ernst Barre", "Privatbrauerei Hofmühl Eichstätt", "Privatbrauerei Sauer & Hartwig",
				"Privatbrauerei Schnaitl", "Privatbrauerei Schweiger", "Privatbrauerei Waldhaus",
				"Privatbrauerei Wittingen", "Propeller Bier", "Pyraser Landbrauerei", "Pyynikin Käsityöläispanimo",
				"Radeberger Gruppe", "Ratsherrn Brauerei", "Rauschend Feine Weine", "Ravenkraft",
				"Riedenburger Brauhaus", "Ritterguts Gose", "Robinsons Brewery", "Rogue Ales & Spirits",
				"Royal Unibrew", "Rügener Insel-Brauerei", "Sabeco", "Schlenkerla (\"Heller-Bräu\" Trum)",
				"Schloßbrauerei Stelzer Fattigau", "Schloss Eggenberg", "Schlossbrauerei Aulendorf",
				"Schlossbrauerei Fürstlich Drehna", "Schlossbrauerei Herrngiersdorf",
				"Schneider Weisse G. Schneider & Sohn", "Schoppe Bräu", "Shepherd Neame", "Sierra Nevada Brewing Co.",
				"Simian (Ales alduin)", "Siren Craft Brew", "Sociedade Central de Cervejas e Bebidas SA",
				"Sol de Copan", "Sommerbecker Brauerei", "Spaten-Franziskaner-Löwenbräu-Gruppe",
				"Spent Brewers Collective", "Spiż", "St. Peter’s Brewery Co.", "Steamworks Brewing Company",
				"Stella Artois", "Stone Brewing", "Stone Brewing - Berlin", "Störtebeker Braumanufaktur", "Straßenbräu",
				"SUN InBev Russia", "Sünner Brauerei und Brennerei", "The Bruery", "Thisted Bryghus",
				"Thornbridge Brewery", "Tilmans Biere", "To Øl", "Trumer Privatbrauerei", "Tsingtao Brewery",
				"Tuatara Brewery", "Tucher Bräu", "Two Brothers Brewing Company", "Tyskie Browary Książęce",
				"UeberQuell Brauwerkstätten", "Uerige Obergärige Hausbrauerei", "Ugly Duck Brewing Co.",
				"Unicer Bebidas", "Unión de Cervecerías Peruanas Backus y Johnston",
				"Urban Chestnut Brewing Company Deutschland", "Velké Březno", "Victory Brewing Company",
				"Vielanker Brauhaus", "Vier Vogel", "Vulkan Brauerei", "Warsteiner", "Weißbierbrauerei Hopf",
				"Weissbräu Schwendl", "Welde Braumanufaktur", "Wiibroe Bryggeri", "Wildwuchs Brauwerk",
				"Will-Bräu - Hochstiftliches Brauhaus in Bayern", "Wolfscraft", "Wychwood Brewery", "Ørbæk Bryggeri");

		for (int i = 0; i < breweryNames.size(); i++) {
			breweryNameRepository.save(new Brewery(breweryNames.get(i)));
		}
		return breweryNames.size() + " Breweries created";
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