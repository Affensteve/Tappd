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
	@GeneratedValue
	@Column(name = "BEER_ID")
	private long id;

	@Column(name = "name")
	private String name;

	@OneToOne(optional = false, cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "BEER_STYLE_ID")
	private BeerStyle beerStyle;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="beer")
	private List<BreweryName> breweryName;

	@Column(name = "color")
	private String color;

	@Column(name = "abv")
	private Integer abv;

	@Column(name = "ibu")
	private Integer ibu;

	@Column(name = "rating")
	private Integer rating;

	protected Beer() {
	}

	public Beer(String name, BeerStyle beerStyle, List<BreweryName> breweryName, String color, Integer abv, Integer ibu,
			Integer rating) {
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

	public Integer getAbv() {
		return abv;
	}

	public void setAbv(Integer abv) {
		this.abv = abv;
	}

	public Integer getIbu() {
		return ibu;
	}

	public void setIbu(Integer ibu) {
		this.ibu = ibu;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public long getId() {
		return id;
	}

}
