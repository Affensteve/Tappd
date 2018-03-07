package de.tappd.Tappd.controller;

import java.util.Arrays;
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
				// @formatter:off
				addNewBeer("Noa Pecan Mud Cake Stout (Bourbon Barrel Aged)", "Stout - Imperial / Double", "Omnipollo", "Tiefschwarz", 11.0, 60.0, 5.0),
				addNewBeer("SLYRS", "Stout - Imperial / Double", "Hoppebräu", "Tiefschwarz", 11.1, 56.0, 5.0),
				addNewBeer("Original Texas Pecan Ice Cream", "Porter - Imperial / Double", "Omnipollo", "Tiefschwarz", 10.0, 0.0, 5.0),
				addNewBeer("Supadupa IPA", "IPA - American", "UeberQuell Brauwerkstätten", "Bernstein", 6.0, 50.0, 4.75),
				addNewBeer("Noa Hazelnut Cupcake", "Stout - Imperial / Double", "Omnipollo", "Tiefschwarz", 11.0, 0.0, 4.75),
				addNewBeer("Sankt Jaro Schwarzbier", "Lager - Dark", "Litovel", "Schwarz", 4.0, 0.0, 4.5), 
				addNewBeer("Gösser Märzen", "Maerzen", "Brauerei Göss", "Heller Bernstein", 5.2, 25.0, 4.5),
				addNewBeer("Magnus 16 Edition Bordeaux", "Barleywine - Other", "Brauhaus Riegele", "Dunkler Bernstein", 12.0, 0.0, 4.5),
				addNewBeer("Torpedo Extra IPA", "IPA - American", "Sierra Nevada Brewing Co.", "Bernstein", 7.2, 65.0, 4.5),
				addNewBeer("Pale Ale", "Pale Ale - American", "Himburgs BrauKunstKeller", "Heller Bernstein", 5.5, 62.0, 4.5),
				addNewBeer("AMARSI", "IPA - Imperial / Double", "Himburgs BrauKunstKeller", "Bernstein", 8.1, 66.0, 4.5),
				addNewBeer("Brooklyn East IPA", "IPA - English", "Brooklyn Brewery", "Bernstein", 6.9, 47.0, 4.5),
				addNewBeer("Shock Series: !!!PA Simcoe & Mosaic", "IPA - Triple", "To Øl", "Bernstein", 13.0, 130.0, 4.5),
				addNewBeer("Gamma Ray", "Pale Ale - American", "Beavertown", "Heller Bernstein", 5.4, 45.0, 4.5),
				addNewBeer("Noa Pecan Mud Cake Stout", "Stout - Imperial / Double", "Omnipollo", "Tiefschwarz", 11.0, 60.0, 4.5),
				addNewBeer("Steve Hops", "Pale Ale - International", "Hanscraft & Co.", "Heller Bernstein", 5.5, 0.0, 4.5),
				addNewBeer("20th Anniversary Encore Series: Stone 12th Anniversary Bitter Chocolate Oatmeal Stout", "Stout - Imperial Oatmeal", "Stone Brewing", "Tiefschwarz", 9.2, 55.0, 4.5),
				addNewBeer("HopDevil IPA", "IPA - American", "Victory Brewing Company", "Bernstein", 6.7, 50.0, 4.5),
				addNewBeer("SHIPA Simcoe (2017)", "IPA - American", "Kehrwieder Kreativbrauerei", "Bernstein", 7.5, 65.0, 4.5),
				addNewBeer("IPA Simcoe", "IPA - American", "Barnstedter Brau-Kunz", "Bernstein", 7.0, 50.0, 4.5), 
				addNewBeer("Pale Ale", "Pale Ale - American", "Berliner Berg Brauerei", "Heller Bernstein", 5.5, 40.0, 4.25), 
				addNewBeer("Vicus Schwarz", "Doppelbock", "Schlossbrauerei Fürstlich Drehna", "Schwarz", 7.4, 0.0, 4.25), 
				addNewBeer("Rotbier", "Lager - Red", "Ratsherrn Brauerei", "Rot", 5.2, 28.0, 4.25), 
				//
				addNewBeer("Mono", "Pale Ale - American", "pibe's Brauerei", "Heller Bernstein", 5.8, 33.0, 4.25), 
				addNewBeer("Jack Hammer", "IPA - American", "BrewDog", "Bernstein", 7.2, 250.0, 4.25), 
				addNewBeer("New England IPA", "IPA - New England", "Ale-Mania", "Bernstein", 6.8, 45.0, 4.25), 
				addNewBeer("Rasputin / Moord & Doodslag", "Stout - Russian Imperial", "Brouwerij De Molen", "Tiefschwarz", 10.4, 46.0, 4.25), 
				addNewBeer("XPA", "IPA - American", "Schoppe Bräu", "Bernstein", 7.0, 70.0, 4.25), 
				addNewBeer("Go To IPA - Berlin", "IPA - Session / India Session Ale", "Stone Brewing - Berlin", "Bernstein", 4.7, 65.0, 4.25), 
				addNewBeer("F60 Paranoid IPA", "IPA - American", "Labieratorium", "Bernstein", 7.3, 85.0, 4.25), 
				addNewBeer("Hop-Stuff", "Pilsner - German", "Adler Brauereigasthof Moosbeuren", "Hell", 4.8, 48.0, 4.25), 
				addNewBeer("Bavarian", "Lager - IPL (India Pale Lager)", "Himburgs BrauKunstKeller", "Heller Bernstein", 5.3, 30.0, 4.25), 
				addNewBeer("Stone Ruination Double IPA 2.0", "IPA - Imperial / Double", "Stone Brewing", "Bernstein", 8.5, 100.0, 4.25), 
				addNewBeer("Pale Ale", "Pale Ale - American", "Vulkan Brauerei", "Heller Bernstein", 4.9, 24.0, 4.25), 
				addNewBeer("The Calling", "IPA - Imperial / Double", "Boulevard Brewing Co.", "Bernstein", 8.5, 75.0, 4.25), 
				addNewBeer("Undertoad", "IPA - New England", "Ugly Duck Brewing Co.", "Bernstein", 7.0, 0.0, 4.25), 
				addNewBeer("Kirg", "IPA - American", "Põhjala", "Bernstein", 6.0, 15.0, 4.25), 
				addNewBeer("Mout & Mocca (2017)", "Stout - Russian Imperial", "Brouwerij De Molen", "Tiefschwarz", 10.0, 81.0, 4.25), 
				addNewBeer("Odessa", "IPA - Rye", "Kehrwieder Kreativbrauerei", "Bernstein", 6.3, 65.0, 4.25), 
				addNewBeer("Bommen & Granaten Rioja Barrel Aged", "Barleywine - English", "Brouwerij De Molen", "Dunkler Bernstein", 11.9, 30.0, 4.25), 
				addNewBeer("Russian Imperial Stout", "Stout - Russian Imperial", "Poppels Bryggeri", "Tiefschwarz", 9.5, 60.0, 4.25), 
				addNewBeer("Stone Xocoveza", "Stout - Imperial Milk / Sweet", "Stone Brewing", "Tiefschwarz", 8.1, 50.0, 4.25), 
				addNewBeer("Mackaper", "Pale Ale - Australian", "Omnipollo", "Heller Bernstein", 6.0, 0.0, 4.25), 
				addNewBeer("Brush (EU Version)", "Stout - Imperial / Double", "Omnipollo", "Tiefschwarz", 12.0, 40.0, 4.25), 
				addNewBeer("Sound Wave", "IPA - American", "Siren Craft Brew", "Bernstein", 5.6, 0.0, 4.25), 
				addNewBeer("Oak Reserve Jahrgang 2013", "Belgian Strong Dark Ale", "Brauerei Rittmayer Hallerndorf", "Dunkler Bernstein", 9.0, 0.0, 4.25), 
				addNewBeer("Or Xata (2016)", "Blonde Ale", "The Bruery", "Heller Bernstein", 7.1, 11.0, 4.25), 
				addNewBeer("Imperial Russian Stout (2016)", "Stout - Russian Imperial", "Stone Brewing", "Tiefschwarz", 10.8, 65.0, 4.25), 
				//
				addNewBeer("Nebuchadnezzar", "IPA - Imperial / Double", "Omnipollo", "Bernstein", 8.5, 100.0, 4.25), 
				addNewBeer("Cocoa Psycho", "Stout - Russian Imperial", "BrewDog", "Tiefschwarz", 10.0, 85.0, 4.25), 
				addNewBeer("Guinness Draught", "Stout - Irish Dry", "Guinness", "Tiefschwarz", 4.2, 45.0, 4.0), 
				addNewBeer("Trooper", "Extra Special / Strong Bitter", "Robinsons Brewery", "Dunkler Bernstein", 4.7, 35.0, 4.0), 
				addNewBeer("Helles", "Lager - Helles", "BRLO", "Heller Bernstein", 5.0, 25.0, 4.0), 
				addNewBeer("German IPA", "IPA - International", "BRLO", "Bernstein", 7.0, 50.0, 4.0), 
				addNewBeer("Maisel & Friends Citrilla", "IPA - White", "Brauerei Gebr. Maisel", "Bernstein", 6.0, 37.0, 4.0), 
				addNewBeer("Pale Ale", "Pale Ale - American", "Camba Bavaria", "Heller Bernstein", 5.3, 31.0, 4.0), 
				addNewBeer("Pilsener", "Pilsner - German", "Flensburger Brauerei Emil Petersen", "Hell", 4.8, 38.0, 4.0), 
				addNewBeer("Lager", "Lager - Pale", "Ratsherrn Brauerei", "Heller Bernstein", 5.4, 22.0, 4.0), 
				addNewBeer("Schwarzbier", "Schwarzbier", "Pyraser Landbrauerei", "Dunkel", 5.2, 20.0, 4.0), 
				addNewBeer("HCMT Helmuts Comet IPA #2", "IPA - English", "Camba Bavaria", "Bernstein", 6.9, 31.0, 4.0), 
				addNewBeer("Bayerisch Ale 2", "Pale Ale - International", "Brauhaus Riegele", "Heller Bernstein", 5.0, 0.0, 4.0), 
				addNewBeer("Flagship IPA", "IPA - New England", "Steamworks Brewing Company", "Bernstein", 6.7, 65.0, 4.0), 
				addNewBeer("Matrosenschluck", "IPA - White", "Ratsherrn Brauerei", "Bernstein", 6.5, 43.0, 4.0), 
				addNewBeer("Hardcore IPA", "IPA - Imperial / Double", "BrewDog", "Bernstein", 9.2, 150.0, 4.0), 
				addNewBeer("Maisel & Friends Stefan's Indian Ale", "IPA - American", "Brauerei Gebr. Maisel", "Bernstein", 7.3, 40.0, 4.0), 
				addNewBeer("Spree Coast IPA", "IPA - American", "Brauerei Lemke", "Bernstein", 6.9, 75.0, 4.0), 
				addNewBeer("India Pale Ale", "IPA - American", "Brauerei Lemke", "Bernstein", 6.5, 60.0, 4.0), 
				addNewBeer("Elvis Juice", "IPA - American", "BrewDog", "Bernstein", 6.5, 40.0, 4.0), 
				addNewBeer("IPA Mania", "IPA - American", "Ale-Mania", "Bernstein", 7.2, 54.0, 4.0), 
				addNewBeer("AC Beer", "Lager - Vienna", "Barnstedter Brau-Kunz", "Heller Bernstein", 9.7, 19.0, 4.0), 
				addNewBeer("LuckyLup", "IPA - International", "Braukunst Gebrüder Wiestner", "Bernstein", 6.5, 40.0, 4.0), 
				addNewBeer("Black Angel IPA", "IPA - Black / Cascadian Dark Ale", "Steamworks Brewing Company", "Bernstein", 7.0, 70.0, 4.0), 
				addNewBeer("Stone Ripper", "Pale Ale - American", "Stone Brewing", "Heller Bernstein", 5.7, 40.0, 4.0), 
				//
				addNewBeer("Tank 7 Farmhouse Ale", "Saison / Farmhouse Ale", "Boulevard Brewing Co.", "Heller Bernstein", 8.5, 38.0, 4.0), 
				addNewBeer("Francis' Big Bangin IPA", "IPA - American", "McGargles", "Bernstein", 7.1, 70.0, 4.0), 
				addNewBeer("American Pale Ale", "Pale Ale - American", "Heidenpeters", "Heller Bernstein", 5.3, 50.0, 4.0), 
				addNewBeer("Maisel & Friends IPA", "IPA - American", "Brauerei Gebr. Maisel", "Bernstein", 6.3, 50.0, 4.0), 
				addNewBeer("Maisel & Friends Choco Porter", "Porter - Baltic", "Brauerei Gebr. Maisel", "Dunkler Bernstein", 6.5, 20.0, 4.0), 
				addNewBeer("Bora", "Lager - Vienna", "Barnstedter Brau-Kunz", "Heller Bernstein", 6.8, 19.0, 4.0), 
				addNewBeer("Amerikanischer Traum", "IPA - American", "Landgang Brauerei", "Bernstein", 6.5, 60.0, 4.0), 
				addNewBeer("Gersdorfer Ale", "Pale Ale - American", "Glückauf Biere", "Heller Bernstein", 6.8, 0.0, 4.0), 
				addNewBeer("Pale Ale", "Pale Ale - American", "Vielanker Brauhaus", "Heller Bernstein", 5.1, 35.0, 4.0), 
				addNewBeer("It's Doomsday", "IPA - American", "Häffner Bräu - Hopfenstopfer", "Bernstein", 6.8, 72.0, 4.0), 
				addNewBeer("Hopfenpflücker Pils", "Pilsner - Other", "Pyraser Landbrauerei", "Hell", 5.0, 0.0, 4.0), 
				addNewBeer("DryPA", "IPA - American", "Ale-Mania", "Bernstein", 7.2, 69.0, 4.0), 
				addNewBeer("High Wire", "Pale Ale - American", "Magic Rock Brewing", "Heller Bernstein", 5.5, 0.0, 4.0), 
				addNewBeer("Punk IPA", "IPA - American", "BrewDog", "Bernstein", 5.6, 45.0, 4.0), 
				addNewBeer("Original Blueberry Slab Cake Ice Cream", "Fruit Beer", "Omnipollo", "rot", 7.0, 0.0, 4.0), 
				addNewBeer("Bommen & Granaten", "Barleywine - Other", "Brouwerij De Molen", "Dunkler Bernstein", 11.9, 30.0, 4.0), 
				addNewBeer("X 9.0 Amarillo IPA", "IPA - American", "CREW Republic", "Bernstein", 6.3, 0.0, 4.0), 
				addNewBeer("Black X-mas", "Bock", "Barnstedter Brau-Kunz", "Schwarz", 8.38, 48.0, 4.0), 
				addNewBeer("Meine Hopfenweisse / Schneider-Brooklyner (Tap5)", "Weizenbock", "Schneider Weisse G. Schneider & Sohn", "Heller Bernstein", 8.2, 40.0, 4.0), 
				addNewBeer("X-Tra Ale", "Belgian Strong Dark Ale", "Wildwuchs Brauwerk", "Schwarz", 8.0, 0.0, 4.0), 
				addNewBeer("DIPA Double India Pale Ale", "IPA - Imperial / Double", "Poppels Bryggeri", "Bernstein", 8.0, 80.0, 4.0), 
				addNewBeer("Tasty Juice", "IPA - American", "LERVIG", "Bernstein", 6.0, 45.0, 4.0), 
				addNewBeer("Cannonball", "IPA - American", "Magic Rock Brewing", "Bernstein", 7.4, 60.0, 4.0), 
				addNewBeer("Virmalised", "IPA - American", "Põhjala", "Bernstein", 6.5, 50.0, 4.0), 
				addNewBeer("Gundry's IPA", "IPA - International", "pibe's Brauerei", "Bernstein", 5.6, 50.0, 4.0), 
				//
				new Beer("Triticum Wormatia",
						beerStyleRepository.findByBeerStylesStartsWithIgnoreCase("Hefeweizen").get(0),
						breweryNameRepository.findByBreweryNameStartsWithIgnoreCase("Barnstedter Brau-Kunz").get(0),
						"Hell Gelb", 5.6, 17.0, 3.75)));
				// @formatter:on
		return beerRepository.findAll().size() + " beers drunk";
	}

	private Beer addNewBeer(String name, String style, String brauerei, String farbe, double abv, double ibu,
			double rating) {
		return new Beer(name, beerStyleRepository.findByBeerStylesStartsWithIgnoreCase(style).get(0),
				breweryNameRepository.findByBreweryNameStartsWithIgnoreCase(brauerei).get(0), farbe, abv, ibu, rating);
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
				"ABK (Aktienbrauerei Kaufbeuren)", "Adler Brauereigasthof Moosbeuren", "Ale-Mania", "Alefried",
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