package com.tripDemoASI_JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tripDemoASI_JPA.model.Trip;

public interface ITripRepository extends JpaRepository<Trip, Integer> {

}
