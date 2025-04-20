package com.rest.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.demo.models.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
	Optional<Estudiante> findByMail(String mail);
}
