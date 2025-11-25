package com.tripDemoASI_JPA;

import com.tripDemoASI_JPA.model.Categoria;
import com.tripDemoASI_JPA.model.Perfil;
import com.tripDemoASI_JPA.model.Trip;
import com.tripDemoASI_JPA.model.Usuario;
import com.tripDemoASI_JPA.repository.ICategoriaRepository;
import com.tripDemoASI_JPA.repository.IPerfilRepository;
import com.tripDemoASI_JPA.repository.ITripRepository;
import com.tripDemoASI_JPA.repository.IUsuarioRepository;

import java.util.Date;
import java.util.LinkedList;
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
	
	@Autowired
	private IPerfilRepository repoPerfil;
	
	@Autowired
	private IUsuarioRepository repoUsuario;


	public static void main(String[] args) {
		SpringApplication.run(TripDemoAsiJpaApplication.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		// Test métodos
		//testConexion();
		guardarCategoria();
		//modificarCategoria();
		//recuperarCategoria();
		//eliminarCategoria();
		//guardarTrip();
		//buscarTrips();
		//crearPerfiles();
		//crearUsuarioConDosPerfiles();
		//getUsuario();
		//buscarTripPorEstatus();
		//buscarTripEntreCosto();
		//buscarTripEstosEstatus();
		//buscarTripsPorPalabraClave();
		//buscarTripsFuturos();
		//buscarPorNombre();
	}
	
	private void buscarPorNombre() {
	    System.out.println("Buscando el trip con nombre exacto 'Caminata'");
	    List<Trip> lista = repoTrip.findByNombre("Caminata");
	    for (Trip t : lista) {
	        System.out.println("Encontrado: " + t.getId() + " - " + t.getNombre());
	    }
	}
	
	private void buscarTripsFuturos() {
        System.out.println("Buscando viajes futuros");
        List<Trip> lista = repoTrip.findByFechaAfter(new Date());  
        for (Trip t : lista) {
            System.out.println(t.getNombre() + " - Fecha: " + t.getFecha());
        }
    }

    private void buscarTripsPorPalabraClave() {
        System.out.println("Buscando viajes que digan 'Caminata' en la descripción");
        List<Trip> lista = repoTrip.findByDescripcionContaining("Caminata");
        for (Trip t : lista) {
            System.out.println(t.getNombre() + " - Descripción: " + t.getDescripcion());
        }
    }
	
	private void buscarTripEstosEstatus() {
		String[] estatus = new String[] {"Aprobada", "Reprobada"};
		List<Trip> lista = repoTrip.findByEstatusIn(estatus);
		for (Trip t : lista)
			System.out.println(t.getId() + ": " + t.getNombre() + " Estatus: " + t.getEstatus());
	}
	
	private void buscarTripEntreCosto() {
		List<Trip> lista = repoTrip.findByCostoBetween(10, 20);
		for (Trip t : lista)
			System.out.println(t.getId() + ": " + t.getNombre() + " Estatus: " + t.getCosto());
	}
	
	private void buscarTripPorEstatus() {
		List<Trip> lista = repoTrip.findByEstatus("Aprobada");
		for (Trip t : lista)
			System.out.println(t.getId() + ": " + t.getNombre() + " Estatus : " + t.getEstatus());
	}
	
	private void getUsuario() {
		Optional<Usuario> usuario = repoUsuario.findById(1);
		if (usuario.isPresent()) {
			Usuario usu = usuario.get();
			System.out.println("Usuario: " + usu.getNombre());
			System.out.println("Perfiles del usuario: ");
			for (Perfil p : usu.getPerfiles()) {
				System.out.println(p.getNombre());
			}
		}else {
			System.out.println("Usuario sin perfiles");
		}
	}
	
	private void crearUsuarioConDosPerfiles() {
		
		Usuario usuario = new Usuario();
		usuario.setNombre("Carlos Cárcamo");
		usuario.setEmail("carlos@gmail.com");
		usuario.setUsername("ccarcamo");
		usuario.setPassword("123");
		usuario.setEstatus(true);
		
		Perfil perfil1 = new Perfil();
		perfil1.setId(1);
		
		Perfil perfil2 = new Perfil();
		perfil2.setId(2);
		
		usuario.agregarPerfil(perfil1);
		usuario.agregarPerfil(perfil2);
		
		repoUsuario.save(usuario);
	}
	
	private void crearPerfiles() {
		
		repoPerfil.saveAll(getListaPerfiles());
	}
	
	private List<Perfil> getListaPerfiles(){
		
		List<Perfil> lista = new LinkedList<Perfil>();
		Perfil perfil1 = new Perfil();
		perfil1.setNombre("Super Administrador");
		
		Perfil perfil2 = new Perfil();
		perfil2.setNombre("Admin");
		
		Perfil perfil3 = new Perfil();
		perfil3.setNombre("Visitante");
		
		lista.add(perfil1);
		lista.add(perfil2);
		lista.add(perfil3);
		
		return lista;
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
		trip.setNombre("Turismo");
		trip.setDescripcion("Caminatas sobre las playas del Oriente de El Salvador");
		trip.setFecha(new Date());
		trip.setCosto(15.0);
		trip.setEstatus("Reprobada");
		trip.setImagen("Trip1.png");
		trip.setDetalles("Detalles de la descripción");
		trip.setCalificacion(8);
		
		Categoria categoria = new Categoria();
		categoria.setId(1);
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
		categoria.setActivo(true);
		categoria.setFecha(new Date());
		
		repoCategoriaService.save(categoria);
	}
	
	private void testConexion() {
		
		if (repoCategoriaService != null)
			System.out.println("Conexion exitosa: " + repoCategoriaService);
		else
			System.out.println("Error");
	}
	

}
