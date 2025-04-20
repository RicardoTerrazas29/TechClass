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

import com.rest.demo.models.Administrador;
import com.rest.demo.repository.AdministradorRepository;

@RestController
@RequestMapping("administrador")
public class AdministradorDAO {

	@Autowired
	private AdministradorRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	// Listar(GET)
	@GetMapping
	public List<Administrador> getAdministrador() {
		return repo.findAll();
	}

	// Listar administrador por ID (GET)
	@GetMapping("/{idAdministrador}")
	public ResponseEntity<Administrador> getProductoById(@PathVariable Integer idAdministrador) {
		Optional<Administrador> optionalAdministrador = repo.findById(idAdministrador);
		if (optionalAdministrador.isPresent()) {
			return ResponseEntity.ok(optionalAdministrador.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// Crear (POST)
	@PostMapping
	public ResponseEntity<String> save(@RequestBody List<Administrador> administradores) {
	    try {
	        for (Administrador administrador : administradores) {
	            // Encriptar la clave antes de guardar
	            String claveEncriptada = passwordEncoder.encode(administrador.getClave());
	            administrador.setClave(claveEncriptada);
	            
	            repo.save(administrador);  // Guardar cada administrador
	        }
	        
	        return ResponseEntity.status(HttpStatus.CREATED).body("Grabado correctamente");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar");
	    }
	}


	// Actualizar administrador por ID (PUT)
	@PutMapping("/{idAdministrador}")
	public ResponseEntity<String> update(@PathVariable Integer idAdministrador, @RequestBody Administrador administrador) { 
		Optional<Administrador> existingadministrador = repo.findById(idAdministrador);
		if (existingadministrador.isPresent()) {
			Administrador a = existingadministrador.get();
			a.setName(administrador.getName());
			a.setMail(administrador.getMail());
			a.setClave(passwordEncoder.encode(administrador.getClave()));
			repo.save(a);
			return ResponseEntity.ok("Editado correctamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontrado");
		}
	}

	// Eliminar (DELETE)
	@DeleteMapping("/{idAdministrador}")
	public ResponseEntity<String> delete(@PathVariable Integer idAdministrador) {
		Optional<Administrador> administrador = repo.findById(idAdministrador);
		if (administrador.isPresent()) {
			repo.deleteById(idAdministrador);
			return ResponseEntity.ok("Eliminado correctamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al eliminar");
		}
	}
}
