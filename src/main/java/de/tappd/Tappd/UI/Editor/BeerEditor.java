package de.tappd.Tappd.UI.Editor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import de.tappd.Tappd.model.Beer;
import de.tappd.Tappd.model.BeerStyle;
import de.tappd.Tappd.model.Brewery;
import de.tappd.Tappd.repo.BeerRepository;
import de.tappd.Tappd.repo.BeerStyleRepository;
import de.tappd.Tappd.repo.BreweryNameRepository;

/**
 * A simple example to introduce building forms. As your real application is
 * probably much more complicated than this example, you could re-use this form
 * in multiple places. This example component is only used in VaadinUI.
 * <p>
 * In a real world application you'll most likely using a common super class for
 * all your forms - less code, better UX. See e.g. AbstractForm in Viritin
 * (https://vaadin.com/addon/viritin).
 */
@SpringComponent
@UIScope
public class BeerEditor extends VerticalLayout {

	private final BeerRepository beerRepository;
	private final BeerStyleRepository beerStyleRepository;
	private final BreweryNameRepository breweryRepository;

	/**
	 * The currently edited beer
	 */
	private Beer beer;

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
	Button cancel = new Button("Cancel");
	@SuppressWarnings("deprecation")
	Button delete = new Button("Delete", FontAwesome.TRASH_O);

	CssLayout actions = new CssLayout(save, cancel, delete);

	Binder<Beer> binder = new Binder<>(Beer.class);

	/**
	 * Used for workaround for Vaadin issue #8858 'Binder.bindInstanceFields()
	 * overwrites existing bindings' https://github.com/vaadin/framework/issues/8858
	 */
	private final Map<String, Component> manualBoundComponents = new HashMap<>();

	@Autowired
	public BeerEditor(BeerRepository repository, BeerStyleRepository beerStyleRepository,
			BreweryNameRepository breweryRepository) {
		this.beerRepository = repository;
		this.beerStyleRepository = beerStyleRepository;
		this.breweryRepository = breweryRepository;

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
		binder.setBean(beer);

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

		addComponents(name, beerStyle, breweryName, color, abv, ibu, rating, actions);

		// Configure and style components
		setSpacing(true);
		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		// wire action buttons to save, delete and reset
		save.addClickListener(e -> {
			beerStyleRepository.save(new BeerStyle(beer.getBeerStyle().toString()));
			breweryRepository.save(new Brewery(beer.getBreweryName().toString()));
			beerRepository.save(new Beer(beer.getName(), beer.getBeerStyle(), beer.getBreweryName(), beer.getColor(),
					beer.getAbv(), beer.getIbu(), beer.getRating()));

		});
		delete.addClickListener(e -> repository.delete(beer));
		cancel.addClickListener(e -> {
			beerStyleRepository.save(beer.getBeerStyle());
			breweryRepository.save(beer.getBreweryName());
			editBeer(beer);
		});
		setVisible(false);
	}

	public interface ChangeHandler {

		void onChange();
	}

	public final void editBeer(Beer c) {
		if (c == null) {
			setVisible(false);
			return;
		}
		final boolean persisted = c.getId() != null;
		if (persisted) {
			// Find fresh entity for editing
			beer = beerRepository.findOne(c.getId());
		} else {
			beer = c;
		}
		cancel.setVisible(persisted);

		// Bind beer properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(beer);

		// setVisible(true);
		setVisible(true);

		// A hack to ensure the whole form is visible
		save.focus();
		// Select all text in name field automatically
		name.selectAll();
	}

	public final void deleteBeer(Beer beer) {
		beerRepository.delete(beer);
	}

	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		save.addClickListener(e -> h.onChange());
		delete.addClickListener(e -> h.onChange());
	}

}
