package de.tappd.Tappd.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import de.tappd.Tappd.model.Brewery;

public interface BreweryNameRepository extends JpaRepository<Brewery, Long> {
	List<Brewery> findByBreweryNameStartsWithIgnoreCase(String breweryName);
}