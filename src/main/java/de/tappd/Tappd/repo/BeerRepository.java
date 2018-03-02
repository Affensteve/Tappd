package de.tappd.Tappd.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tappd.Tappd.model.Beer;

public interface BeerRepository extends JpaRepository<Beer, Long>{
	List<Beer> findByNameStartsWithIgnoreCase(String lastName);
}