package de.tappd.Tappd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Beer")
public class Beer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BEER_ID")
	private long id;

	private String name;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "BEER_STYLE_ID")
	private BeerStyle beerStyle;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy="beer")
	//@JoinTable() inversiv columns und 'nornale'
	private List<BreweryName> breweryName;

	private String color;

	private Double abv;

	private Double ibu;

	private Double rating;

	protected Beer() {
	}

	public Beer(String name, BeerStyle beerStyle, List<BreweryName> breweryName, String color, Integer abv, Integer ibu,
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

	public List<BreweryName> getBreweryName() {
		return breweryName;
	}

	public void setBreweryName(List<BreweryName> breweryName) {
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

	public long getId() {
		return id;
	}

}
