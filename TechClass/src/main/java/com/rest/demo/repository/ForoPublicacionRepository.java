package com.rest.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.demo.models.ForoPublicacion;

public interface ForoPublicacionRepository extends JpaRepository<ForoPublicacion, Integer> {
	
}