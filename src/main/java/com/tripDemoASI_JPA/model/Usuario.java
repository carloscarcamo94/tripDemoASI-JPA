package com.tripDemoASI_JPA.model;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String email;
	private String username;
	private String password;
	private boolean estatus;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "perfil_usuario",
				joinColumns = @JoinColumn(name = "Usuario_id"),
				inverseJoinColumns = @JoinColumn(name = "Perfil_id"))
	private List<Perfil> perfiles;
	
	public void agregarPerfil (Perfil tempPerfil) {
		if (perfiles == null)
			perfiles = new LinkedList<Perfil>();
		perfiles.add(tempPerfil);
	}
	
	public List<Perfil> getPerfiles() {
        return perfiles;
    }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEstatus() {
		return estatus;
	}
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	

	
}
