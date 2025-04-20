package com.rest.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.demo.models.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
	Optional<Profesor> findByMail(String mail);
}
