package com.rest.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import com.rest.demo.models.Logro;


public interface LogroRepository extends JpaRepository<Logro, Integer> {
    List<Logro> findByCursoIdCurso(Integer idCurso);
    List<Logro> findByContenidoIdContenido(Integer idContenido);
}
