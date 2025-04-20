package com.rest.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.demo.models.Estudiante;
import com.rest.demo.repository.EstudianteRepository;

@RestController
@RequestMapping("estudiante")
public class EstudianteDAO {

	@Autowired
	private EstudianteRepository repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Listar(GET)
	@GetMapping
	public List<Estudiante> getEstudiante() {
		return repo.findAll();
	}

	// Listar administrador por ID (GET)
	@GetMapping("/{idEstudiante}")
	public ResponseEntity<Estudiante> getProductoById(@PathVariable Integer idEstudiante) {
		Optional<Estudiante> optionalEstudiante = repo.findById(idEstudiante);
		if (optionalEstudiante.isPresent()) {
			return ResponseEntity.ok(optionalEstudiante.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// Crear (POST)
	@PostMapping
	public ResponseEntity<String> save(@RequestBody List<Estudiante> estudiantes) {
	    try {
	        for (Estudiante estudiante : estudiantes) {
	            // Encriptar la clave antes de guardar
	            String claveEncriptada = passwordEncoder.encode(estudiante.getClave());
	            estudiante.setClave(claveEncriptada);
	            
	            repo.save(estudiante);  // Guardar cada estudiante
	        }
	        return ResponseEntity.status(HttpStatus.CREATED).body("Grabado correctamente");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar");
	    }
	}


	// Actualizar administrador por ID (PUT)
	@PutMapping("/{idEstudiante}")
	public ResponseEntity<String> update(@PathVariable Integer idEstudiante, @RequestBody Estudiante estudiante) {
		Optional<Estudiante> existingestudiante = repo.findById(idEstudiante);
		if (existingestudiante.isPresent()) {
			Estudiante e = existingestudiante.get();
			e.setName(estudiante.getName());
			e.setDni(estudiante.getDni());
			e.setAddress(estudiante.getAddress());
			e.setMail(estudiante.getMail());
			e.setClave(passwordEncoder.encode(estudiante.getClave()));
			repo.save(e);
			return ResponseEntity.ok("Editado correctamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontrado");
		}
	}

	// Eliminar (DELETE)
	@DeleteMapping("/{idEstudiante}")
	public ResponseEntity<String> delete(@PathVariable Integer idEstudiante) {
		Optional<Estudiante> estudiante = repo.findById(idEstudiante);
		if (estudiante.isPresent()) {
			repo.deleteById(idEstudiante);
			return ResponseEntity.ok("Eliminado correctamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al eliminar");
		}
	}

}
