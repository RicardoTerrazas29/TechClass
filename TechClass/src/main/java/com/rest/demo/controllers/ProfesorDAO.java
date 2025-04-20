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

import com.rest.demo.models.Profesor;
import com.rest.demo.repository.ProfesorRepository;

@RestController
@RequestMapping("profesor")
public class ProfesorDAO {

	@Autowired
	private ProfesorRepository repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Listar(GET)
	@GetMapping
	public List<Profesor> getProfesor() {
		return repo.findAll();
	}

	// Listar administrador por ID (GET)
	@GetMapping("/{idProfesor}")
	public ResponseEntity<Profesor> getProductoById(@PathVariable Integer idProfesor) {
		Optional<Profesor> optionalProfesor = repo.findById(idProfesor);
		if (optionalProfesor.isPresent()) {
			return ResponseEntity.ok(optionalProfesor.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// Crear (POST)
	@PostMapping
	public ResponseEntity<String> save(@RequestBody List<Profesor> profesores) {
	    try {
	        for (Profesor profesor : profesores) {
	            // Encriptar la clave antes de guardar
	            String claveEncriptada = passwordEncoder.encode(profesor.getClave());
	            profesor.setClave(claveEncriptada);
	            
	            repo.save(profesor);  // Guardar cada profesor
	        }
	        return ResponseEntity.status(HttpStatus.CREATED).body("Grabado correctamente");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar");
	    }
	}


	// Actualizar administrador por ID (PUT)
	@PutMapping("/{idProfesor}")
	public ResponseEntity<String> update(@PathVariable Integer idProfesor, @RequestBody Profesor profesor) {
		Optional<Profesor> existingprofesor = repo.findById(idProfesor);
		if (existingprofesor.isPresent()) {
			Profesor p = existingprofesor.get();
			p.setName(profesor.getName());
			p.setPhone(profesor.getPhone());
			p.setMail(profesor.getMail());
			p.setClave(passwordEncoder.encode(profesor.getClave()));
			repo.save(p);
			return ResponseEntity.ok("Editado correctamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontrado");
		}
	}

	// Eliminar (DELETE)
	@DeleteMapping("/{idProfesor}")
	public ResponseEntity<String> delete(@PathVariable Integer idProfesor) {
		Optional<Profesor> profesor = repo.findById(idProfesor);
		if (profesor.isPresent()) {
			repo.deleteById(idProfesor);
			return ResponseEntity.ok("Eliminado correctamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al eliminar");
		}
	}
}
