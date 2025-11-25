package com.tripDemoASI_JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tripDemoASI_JPA.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{

}
