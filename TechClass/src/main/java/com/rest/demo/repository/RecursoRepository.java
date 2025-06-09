package com.rest.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.demo.models.Recurso;

public interface RecursoRepository extends JpaRepository<Recurso, Integer> {} 
