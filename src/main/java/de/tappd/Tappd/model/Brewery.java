package de.tappd.Tappd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Brewery")
public class Brewery implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String breweryName;

	protected Brewery() {
	}

	public Brewery(String breweryName) {
		this.breweryName = breweryName;
	}

	public String getBreweryName() {
		return breweryName;
	}

	public void setBreweryName(String breweryName) {
		this.breweryName = breweryName;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Brewery[id=%d, breweryName='%s']", id, breweryName);
	}

}