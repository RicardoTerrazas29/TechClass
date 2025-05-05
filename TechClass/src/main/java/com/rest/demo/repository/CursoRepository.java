package com.rest.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rest.demo.models.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
