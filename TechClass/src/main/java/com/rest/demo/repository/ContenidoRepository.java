package com.rest.demo.repository;

import com.rest.demo.models.Contenido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenidoRepository extends JpaRepository<Contenido, Integer> {
	List<Contenido> findByCursoIdCurso(Integer idCurso);
}
