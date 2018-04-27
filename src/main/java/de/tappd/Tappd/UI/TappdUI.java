package de.tappd.Tappd.UI;

import static com.vaadin.server.Sizeable.Unit.PIXELS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import de.tappd.Tappd.model.Beer;
import de.tappd.Tappd.model.BeerStyle;
import de.tappd.Tappd.model.Brewery;
import de.tappd.Tappd.model.style.BeerStyles;
import de.tappd.Tappd.process.BeerProcess;
import de.tappd.Tappd.repo.BeerRepository;
import de.tappd.Tappd.repo.BeerStyleRepository;
import de.tappd.Tappd.repo.BreweryNameRepository;

@SpringUI
@Theme("valo")
public class TappdUI extends UI {

	private final BeerRepository repo;
	private final BeerStyleRepository beerStyleRepository;
	private final BreweryNameRepository breweryRepository;

	final Grid<Beer> grid;

	final TextField filter;

	private final Button addNewBtn;
	private final Button addEditBtn;
	private final Button addDeleteBtn;

	private Beer editBeer;

	final Window window = new Window("Beer");

	/* Fields to edit properties in Customer entity */
	TextField name = new TextField("Name");
	ComboBox<BeerStyle> beerStyle = new ComboBox<>("Beer Style");
	ComboBox<Brewery> breweryName = new ComboBox<>("Brewery name");
	TextField color = new TextField("Color");

	TextField abv = new TextField("ABV");
	TextField ibu = new TextField("IBU");
	TextField rating = new TextField("Rating");

	/* Action buttons */
	@SuppressWarnings("deprecation")
	Button save = new Button("Save", FontAwesome.SAVE);
	Button cancel = new Button("Cancel", VaadinIcons.CLOSE);
	HorizontalLayout actions = new HorizontalLayout(save, cancel);

	Binder<Beer> binder = new Binder<>(Beer.class);

	@Autowired
	BeerProcess beerProcess;

	/**
	 * Used for workaround for Vaadin issue #8858 'Binder.bindInstanceFields()
	 * overwrites existing bindings' https://github.com/vaadin/framework/issues/8858
	 */
	private final Map<String, Component> manualBoundComponents = new HashMap<>();

	@SuppressWarnings("deprecation")
	@Autowired
	public TappdUI(BeerRepository repo, BeerStyleRepository beerStyleRepository,
			BreweryNameRepository breweryRepository) {
		this.repo = repo;
		this.beerStyleRepository = beerStyleRepository;
		this.breweryRepository = breweryRepository;

		this.grid = new Grid<>(Beer.class);
		this.filter = new TextField();
		this.addNewBtn = new Button("New Beer", VaadinIcons.PLUS);
		this.addEditBtn = new Button("Edit Beer", VaadinIcons.EDIT);
		this.addDeleteBtn = new Button("Drink Beer", FontAwesome.TRASH_O);

		addComponentsForDialog();

		window.setWidth(450f, PIXELS);
		final VerticalLayout content = new VerticalLayout();
		content.setWidth(450f, PIXELS);
		content.setMargin(true);
		name.setWidth("400px");
		beerStyle.setWidth("400px");
		breweryName.setWidth("400px");
		color.setWidth("400px");
		abv.setWidth("400px");
		ibu.setWidth("400px");
		rating.setWidth("400px");

		content.addComponents(name, beerStyle, breweryName, color, abv, ibu, rating, actions);

		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		// wire action buttons to save, cancel
		save.addClickListener(e -> {
			beerProcess.saveBeer(editBeer);
			removeWindow(window);
			listBeers(filter.getValue());
		});
		cancel.addClickListener(e -> {
			removeWindow(window);
		});

		window.setContent(content);
		window.setModal(true);
		window.setResizable(false);
		window.center();
	}

	private void addComponentsForDialog() {
		// Declared local here to prevent processing by Binder#bindInstanceFields()
		manualBoundComponents.put("abv", abv);
		binder.forField(abv).withNullRepresentation("")
				.withConverter(new StringToDoubleConverter(Double.valueOf(0), "doubles only"))
				.bind(Beer::getAbv, Beer::setAbv);

		manualBoundComponents.put("ibu", ibu);
		binder.forField(ibu).withNullRepresentation("")
				.withConverter(new StringToDoubleConverter(Double.valueOf(0), "doubles only"))
				.bind(Beer::getIbu, Beer::setIbu);

		manualBoundComponents.put("rating", rating);
		binder.forField(rating).withNullRepresentation("")
				.withConverter(new StringToDoubleConverter(Double.valueOf(0), "doubles only"))
				.bind(Beer::getRating, Beer::setRating);

		// bind using naming convention
		binder.bindInstanceFields(this);
		binder.setBean(editBeer);

		List<BeerStyle> stylerList = beerStyleRepository.findAll();
		beerStyle.setItems(stylerList);

		// Use the name property for item captions
		beerStyle.setItemCaptionGenerator(BeerStyle::getBeerStyles);

		// Allow adding new items and add
		// handling for new items
		beerStyle.setNewItemHandler(inputString -> {
			BeerStyle newStyle = new BeerStyle(inputString);
			stylerList.add(newStyle);

			// Update combobox content
			beerStyle.setItems(stylerList);

			// Remember to set the selection to the new item
			beerStyle.setSelectedItem(newStyle);
		});

		List<Brewery> breweryList = breweryRepository.findAll();
		breweryName.setItems(breweryList);

		// Use the name property for item captions
		breweryName.setItemCaptionGenerator(Brewery::getBreweryName);

		// Allow adding new items and add
		// handling for new items
		breweryName.setNewItemHandler(inputString -> {
			Brewery newOne = new Brewery(inputString);
			breweryList.add(newOne);

			// Update combobox content
			breweryName.setItems(breweryList);

			// Remember to set the selection to the new item
			breweryName.setSelectedItem(newOne);
		});
	}

	@Override
	protected void init(VaadinRequest request) {
		// build layout
		HorizontalLayout heading = new HorizontalLayout(new Label("Brau-Kunz' Biergarten"));
		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn, addEditBtn, addDeleteBtn);
		VerticalLayout mainLayout = new VerticalLayout(heading, actions, grid);
		setContent(mainLayout);

		disableButtonsIfNoFocus(false);

		grid.setHeight(800, Unit.PIXELS);
		grid.setWidth("100%");
		grid.setColumns("id", "name", "beerStyle", "breweryName", "color", "abv", "ibu", "rating");

		filter.setPlaceholder("Filter by name");

		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		filter.setValueChangeMode(ValueChangeMode.LAZY);
		filter.addValueChangeListener(e -> listBeers(e.getValue()));

		// Connect selected beer to editor or hide if none is selected
		grid.asSingleSelect().addValueChangeListener(e -> {
			disableButtonsIfNoFocus(e.getValue() != null);
			editBeer = e.getValue();
		});

		// Instantiate and edit new Customer the new button is clicked
		// String name, BeerStyle beerStyle, List<BreweryName> breweryName, String
		// color, Double abv, Double ibu,Double rating
		addNewBtn.addClickListener(
				e -> editBeer(new Beer("", new BeerStyle(""), new Brewery(""), "", 0.0, 0.0, 0.0), "New Beer"));

		addEditBtn.addClickListener(e -> editBeer(editBeer, "Edit Beer"));
		addDeleteBtn.addClickListener(e -> {
			repo.delete(editBeer);
			listBeers(filter.getValue());
		});

		// Initialize listing
		listBeers(null);
	}

	public final void editBeer(Beer beer, String title) {
		if (beer == null) {
			removeWindow(window);
			return;
		}
		final boolean persisted = beer.getId() != null;
		if (persisted) {
			// Find fresh entity for editing
			editBeer = repo.findOne(beer.getId());
		} else {
			editBeer = beer;
		}
		cancel.setVisible(persisted);

		// Bind beer properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(editBeer);

		// setVisible(true);
		window.setCaption(title);
		addWindow(window);

		// A hack to ensure the whole form is visible
		save.focus();
		// Select all text in name field automatically
		name.selectAll();
	}

	private void disableButtonsIfNoFocus(boolean state) {
		addEditBtn.setEnabled(state);
		addDeleteBtn.setEnabled(state);
	}

	// tag::listCustomers[]
	void listBeers(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			grid.setItems(repo.findAll());
		} else {
			grid.setItems(repo.findByNameStartsWithIgnoreCase(filterText));
		}
	}
	// end::listCustomers[]

}