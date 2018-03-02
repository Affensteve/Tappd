package de.tappd.Tappd.UI.Editor;

import java.util.HashMap;
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
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.tappd.Tappd.model.Beer;
import de.tappd.Tappd.model.BeerStyle;
import de.tappd.Tappd.repo.BeerRepository;

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

	private final BeerRepository repository;

	/**
	 * The currently edited customer
	 */
	private Beer beer;

	/* Fields to edit properties in Customer entity */
	// String name, String beerStyle, List<BreweryName> breweryName, String
	// color, Double abv, Double ibu, Double rating
	TextField name = new TextField("Name");
	TextField beerStyle = new TextField();
	TextField breweryName = new TextField("Brewery name");
	TextField color = new TextField("Color");

	TextField abv = new TextField("ABV");
	TextField ibu = new TextField("IBU");
	TextField rating = new TextField("Rating");

	/* Action buttons */
	Button save = new Button("Save", FontAwesome.SAVE);
	Button cancel = new Button("Cancel");
	Button delete = new Button("Delete", FontAwesome.TRASH_O);
	CssLayout actions = new CssLayout(save, cancel, delete);

	Binder<Beer> binder = new Binder<>(Beer.class);
	private final Map<String, Component> manualBoundComponents = new HashMap<>();

	@Autowired
	public BeerEditor(BeerRepository repository) {
		this.repository = repository;

		// Workaround for Vaadin issue #8858
		// Declared local here to prevent processing by Binder#bindInstanceFields()
		manualBoundComponents.put("abv", abv);
		binder.forField(abv).withNullRepresentation("")
				.withConverter(new StringToDoubleConverter(Double.valueOf(0), "doubles only"))
				.bind(Beer::getAbv, Beer::setAbv);
		binder.bindInstanceFields(this);

		manualBoundComponents.put("ibu", ibu);
		binder.forField(ibu).withNullRepresentation("")
				.withConverter(new StringToDoubleConverter(Double.valueOf(0), "doubles only"))
				.bind(Beer::getIbu, Beer::setIbu);
		binder.bindInstanceFields(this);

		manualBoundComponents.put("rating", rating);
		binder.forField(rating).withNullRepresentation("")
				.withConverter(new StringToDoubleConverter(Double.valueOf(0), "doubles only"))
				.bind(Beer::getRating, Beer::setRating);
		binder.bindInstanceFields(this);

		// Workaround for Vaadin issue #8858
		addComponent(manualBoundComponents.get("abv"));
		addComponent(manualBoundComponents.get("ibu"));
		addComponent(manualBoundComponents.get("rating"));
		addComponents(name, beerStyle, breweryName, color, actions);

		// bind using naming convention
		binder.bindInstanceFields(this);

		// Configure and style components
		setSpacing(true);
		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		// wire action buttons to save, delete and reset
		save.addClickListener(e -> repository.save(beer));
		delete.addClickListener(e -> repository.delete(beer));
		cancel.addClickListener(e -> editBeer(beer));
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
			beer = repository.findOne(c.getId());
		} else {
			beer = c;
		}
		cancel.setVisible(persisted);

		// Bind customer properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(beer);

		setVisible(true);

		// A hack to ensure the whole form is visible
		save.focus();
		// Select all text in firstName field automatically
		name.selectAll();
	}

	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		save.addClickListener(e -> h.onChange());
		delete.addClickListener(e -> h.onChange());
	}

}