package com.rest.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rest.demo.models.LogroEstudiante;

public interface LogroEstudianteRepository extends JpaRepository<LogroEstudiante,Integer> {
    List<LogroEstudiante> findByEstudianteIdEstudiante(Integer idEstudiante);
    List<LogroEstudiante> findByLogroIdLogro(Integer idLogro);
}
