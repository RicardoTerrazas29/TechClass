package com.rest.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.demo.models.Recurso;

public interface RecursoRepository extends JpaRepository<Recurso, Integer> {
    List<Recurso> findByContenidoIdContenido(Integer idContenido);
} 
