package com.rest.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rest.demo.models.AsignacionCurso;

public interface AsignacionCursoRepository extends JpaRepository<AsignacionCurso, Integer> {
    List<AsignacionCurso> findByEstudianteIdEstudiante(Integer idEstudiante);
    List<AsignacionCurso> findByCursoIdCurso(Integer idCurso);
}