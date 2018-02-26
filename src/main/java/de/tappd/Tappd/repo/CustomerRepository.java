package de.tappd.Tappd.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tappd.Tappd.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);
}