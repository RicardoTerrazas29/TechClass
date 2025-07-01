package com.rest.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.demo.models.RecursoRevisado;

public interface RecursoRevisadoRepository extends JpaRepository<RecursoRevisado,Integer>{
  boolean existsByEstudianteIdEstudianteAndRecursoIdRecurso(Integer idEstudiante, Integer idRecurso);
  List<RecursoRevisado> findByEstudianteIdEstudianteAndContenidoIdContenido(Integer idEstudiante, Integer idContenido);
}
