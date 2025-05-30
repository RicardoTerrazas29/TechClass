package com.rest.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.demo.models.ForoComentario;

public interface ForoComentarioRepository extends JpaRepository<ForoComentario, Integer> {
	
}