package de.tappd.Tappd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BreweryName")
public class BreweryName implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "BEWERY_NAME_ID")
	private long id;

	@Column(name = "brewery_name")
	private String breweryName;

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bewerey_name_id")
	private Beer beer;
	
	public BreweryName() {
	}

	public BreweryName(String breweryName) {
		super();
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

}
