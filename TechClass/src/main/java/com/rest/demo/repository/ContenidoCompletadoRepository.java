package com.rest.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.demo.models.ContenidoCompletado;

public interface ContenidoCompletadoRepository extends JpaRepository<ContenidoCompletado, Integer>{
    boolean existsByEstudianteIdEstudianteAndContenidoIdContenido(Integer idEstudiante, Integer idContenido);
}
