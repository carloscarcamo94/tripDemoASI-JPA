package com.tripDemoASI_JPA;

import com.tripDemoASI_JPA.model.Categoria;
import com.tripDemoASI_JPA.model.Trip;
import com.tripDemoASI_JPA.repository.ICategoriaRepository;
import com.tripDemoASI_JPA.repository.ITripRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TripDemoAsiJpaApplication implements CommandLineRunner{

	@Autowired
    private ICategoriaRepository repoCategoriaService;
	
	@Autowired
	private ITripRepository repoTrip;


	public static void main(String[] args) {
		SpringApplication.run(TripDemoAsiJpaApplication.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		// Test métodos
		//testConexion();
		//guardarCategoria();
		//modificarCategoria();
		//recuperarCategoria();
		//eliminarCategoria();
		//guardarTrip();
		buscarTrips();
	}
	
	private void buscarTrips() {
		List<Trip> lista = repoTrip.findAll();
		for (Trip trip: lista) {
			System.out.println("Nombre: " + trip.getNombre());
			System.out.println("Costo: " + trip.getCosto());
			System.out.println("Categoría: " + trip.getCategoria().getNombre());
			System.out.println("Descripción: " + trip.getDescripcion());
		}
	}
	
	private void guardarTrip() {
		System.out.println("Intentando insertar un trip");
		Trip trip = new Trip();
		trip.setNombre("Caminata");
		trip.setDescripcion("Caminatas sobre las playas de El Salvador");
		trip.setFecha(new Date());
		trip.setCosto(15.0);
		trip.setEstatus("Aprobada");
		trip.setImagen("Trip1.png");
		trip.setDetalles("Detalles de la descripción");
		
		Categoria categoria = new Categoria();
		categoria.setId(2);
		trip.setCategoria(categoria);
		
		repoTrip.save(trip);
	}
	
	private void eliminarCategoria() {
		//Categoria categoria = new Categoria();
		
		Optional <Categoria> optional = repoCategoriaService.findById(1);
		
		if (optional.isPresent()) {
		    repoCategoriaService.delete(optional.get());
		    System.out.println("Categoría eliminada correctamente.");
		}
		else
			System.out.println("Categoría no existe");
	}
	
	private void recuperarCategoria() {
		//Categoria categoria = new Categoria();
		
		Optional <Categoria> optional = repoCategoriaService.findById(3);
		
		if (optional.isPresent()) {
			System.out.println(optional.get().getNombre() + " Descripción: " + optional.get().getDescripcion());
		}
		else
			System.out.println("Categoría no existe");
	}
	
	private void modificarCategoria() {
		Categoria categoria = new Categoria();
		categoria.setId(1);
		categoria.setNombre("Pueblos Vivos");
		categoria.setDescripcion("Hermosos y pintorescos paseos por pueblos de El Salvador");
		
		repoCategoriaService.save(categoria);
	}
	
	private void guardarCategoria() {
		Categoria categoria = new Categoria();
		categoria.setNombre("Playas");
		categoria.setDescripcion("Está categoría está dedicada a las hermosas playas de El Salvador");
		
		repoCategoriaService.save(categoria);
	}
	
	private void testConexion() {
		
		if (repoCategoriaService != null)
			System.out.println("Conexion exitosa: " + repoCategoriaService);
		else
			System.out.println("Error");
	}
	

}
