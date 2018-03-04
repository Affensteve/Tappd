package de.tappd.Tappd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.tappd.Tappd.model.style.BeerStyles;

@Entity
@Table(name = "BeerStyle")
public class BeerStyle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String beerStyles;

	protected BeerStyle() {
	}

	public BeerStyle(String beerStyles) {
		this.beerStyles = beerStyles;
	}

	public String getBeerStyles() {
		return beerStyles;
	}

	public void setBeerStyles(String beerStyles) {
		this.beerStyles = beerStyles;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return beerStyles;
	}

}
