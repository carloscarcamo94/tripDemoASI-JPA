package com.tripDemoASI_JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tripDemoASI_JPA.model.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer>{

	//métodos extras
}
