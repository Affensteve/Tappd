package de.tappd.Tappd.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Beer")
public class Beer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	private String name;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "BEER_STYLE_ID")
	private BeerStyle beerStyle;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "Brewery_Name_ID")
	private Brewery breweryName;

	private String color;

	private Double abv;

	private Double ibu;

	private Double rating;

	protected Beer() {
	}

	public Beer(String name, BeerStyle beerStyle, Brewery breweryName, String color, Double abv, Double ibu,
			Double rating) {
		this.name = name;
		this.beerStyle = beerStyle;
		this.breweryName = breweryName;
		this.color = color;
		this.abv = abv;
		this.ibu = ibu;
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BeerStyle getBeerStyle() {
		return beerStyle;
	}

	public void setBeerStyle(BeerStyle beerStyle) {
		this.beerStyle = beerStyle;
	}

	public Brewery getBreweryName() {
		return breweryName;
	}

	public void setBreweryName(Brewery breweryName) {
		this.breweryName = breweryName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getAbv() {
		return abv;
	}

	public void setAbv(Double abv) {
		this.abv = abv;
	}

	public Double getIbu() {
		return ibu;
	}

	public void setIbu(Double ibu) {
		this.ibu = ibu;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format(
				"Beer[id=%d, name='%s', beerStyle='%s', breweryName='%s', color='%s', abv='%d',ibu='%d',rating='%d']",
				id, name, beerStyle, breweryName, color, abv, ibu, rating);
	}

}
