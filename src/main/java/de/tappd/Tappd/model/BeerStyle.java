package de.tappd.Tappd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.tappd.Tappd.model.style.BeerStyles;

@Entity
@Table(name = "BeerStyle")
public class BeerStyle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "BEER_STYLE_ID")
	private long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "BEER_STYLES")
	private BeerStyles beerStyles;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "beerStyle")
	private Beer beer;

	public BeerStyle() {

	}

	public BeerStyle(BeerStyles beerStyles) {
		this.beerStyles = beerStyles;
	}

	public BeerStyles getBeerStyles() {
		return beerStyles;
	}

	public void setBeerStyles(BeerStyles beerStyles) {
		this.beerStyles = beerStyles;
	}

	public long getId() {
		return id;
	}

}
