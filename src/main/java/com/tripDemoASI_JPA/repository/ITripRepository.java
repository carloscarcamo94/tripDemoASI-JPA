package com.tripDemoASI_JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tripDemoASI_JPA.model.Trip;

import java.util.Date;
import java.util.List;


public interface ITripRepository extends JpaRepository<Trip, Integer> {

	// Query Methods
	// Select * from trip where estatus = ?
	List<Trip> findByEstatus(String estatus);
	
	List<Trip> findByCostoBetween(double costo1, double costo2);
	
	List<Trip> findByEstatusIn(String[] estatus);
	
    List<Trip> findByFechaAfter(Date fecha);

    List<Trip> findByDescripcionContaining(String texto);
    
    List<Trip> findByNombre(String nombre);
}
