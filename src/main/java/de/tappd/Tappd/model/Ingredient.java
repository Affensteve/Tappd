package de.tappd.Tappd.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Ingredient")
public class Ingredient implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String ingredient;

	protected Ingredient() {
	}

	public Ingredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getRating() {
		return ingredient;
	}

	public void setRating(String ingredient) {
		this.ingredient = ingredient;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

}
