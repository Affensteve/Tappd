package de.tappd.Tappd.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import de.tappd.Tappd.model.BeerStyle;

public interface BeerStyleRepository extends JpaRepository<BeerStyle, Long> {
	List<BeerStyle> findByBeerStylesStartsWithIgnoreCase(String beerStyle);
}