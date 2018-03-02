package de.tappd.Tappd.UI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.tappd.Tappd.model.Beer;
import de.tappd.Tappd.repo.BeerRepository;

@SpringUI
@Theme("valo")
public class TappdUI extends UI {

	private final BeerRepository repo;

	// private final BeerEditor editor;

	final Grid<Beer> grid;

	final TextField filter;

	private final Button addNewBtn;

	@Autowired
	public TappdUI(BeerRepository repo/* , BeerEditor editor */) {
		this.repo = repo;
		// this.editor = editor;
		this.grid = new Grid<>(Beer.class);
		this.filter = new TextField();
		this.addNewBtn = new Button("New beer", FontAwesome.PLUS);
	}

	@Override
	protected void init(VaadinRequest request) {
		// build layout
		HorizontalLayout heading = new HorizontalLayout(new Label("Brau-Kunz' Biergarten"));
		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
		VerticalLayout mainLayout = new VerticalLayout(heading, actions, grid);
		setContent(mainLayout);

		grid.setHeight(500, Unit.PIXELS);
		grid.setColumns("id", "name", "beerStyle", "breweryName", "color", "abv", "ibu", "rating");

		filter.setPlaceholder("Filter by name");

		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		filter.setValueChangeMode(ValueChangeMode.LAZY);
		filter.addValueChangeListener(e -> listBeers(e.getValue()));

		// Connect selected Customer to editor or hide if none is selected
		// grid.asSingleSelect().addValueChangeListener(e -> {
		// editor.editBeer(e.getValue());
		// });

		// Instantiate and edit new Customer the new button is clicked
		// String name, BeerStyle beerStyle, List<BreweryName> breweryName, String
		// color, Double abv, Double ibu,Double rating
		// addNewBtn.addClickListener(e -> editor
		// .editBeer(new Beer("", new BeerStyle(), Arrays.asList(new BreweryName("")),
		// "", 0.0, 0.0, 0.0)));

		// Listen changes made by the editor, refresh data from backend
		// editor.setChangeHandler(() -> {
		// editor.setVisible(false);
		// listBeers(filter.getValue());
		// });

		// Initialize listing
		listBeers(null);
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