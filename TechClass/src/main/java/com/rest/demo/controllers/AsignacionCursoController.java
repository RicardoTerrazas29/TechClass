package com.rest.demo.controllers;

import com.rest.demo.dto.AsignacionCursoDTO;
import com.rest.demo.models.AsignacionCurso;
import com.rest.demo.models.Curso;
import com.rest.demo.models.Estudiante;
import com.rest.demo.repository.AsignacionCursoRepository;
import com.rest.demo.repository.CursoRepository;
import com.rest.demo.repository.EstudianteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asignaciones")
public class AsignacionCursoController {

    private final AsignacionCursoRepository asignacionCursoRepository;
    private final CursoRepository cursoRepository;
    private final EstudianteRepository estudianteRepository;

    public AsignacionCursoController(AsignacionCursoRepository asignacionCursoRepository,
                                     CursoRepository cursoRepository,
                                     EstudianteRepository estudianteRepository) {
        this.asignacionCursoRepository = asignacionCursoRepository;
        this.cursoRepository = cursoRepository;
        this.estudianteRepository = estudianteRepository;
    }

    // Listar todas las asignaciones
    @GetMapping
    public List<AsignacionCurso> listarAsignaciones() {
        return asignacionCursoRepository.findAll();
    }

    // Crear nueva asignación
    @PostMapping
    public ResponseEntity<?> crearAsignacion(@RequestBody AsignacionCursoDTO dto) {
        Optional<Curso> curso = cursoRepository.findById(dto.getIdCurso());
        Optional<Estudiante> estudiante = estudianteRepository.findById(dto.getIdEstudiante());

        if (curso.isEmpty() || estudiante.isEmpty()) {
            return ResponseEntity.badRequest().body("Curso o Estudiante no encontrado");
        }

        AsignacionCurso nueva = new AsignacionCurso();
        nueva.setCurso(curso.get());
        nueva.setEstudiante(estudiante.get());

        AsignacionCurso guardada = asignacionCursoRepository.save(nueva);
        return ResponseEntity.ok(guardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAsignacion(@PathVariable Integer id, @RequestBody AsignacionCursoDTO dto) {
        Optional<AsignacionCurso> asignacionOpt = asignacionCursoRepository.findById(id);
        Optional<Curso> curso = cursoRepository.findById(dto.getIdCurso());
        Optional<Estudiante> estudiante = estudianteRepository.findById(dto.getIdEstudiante());

        if (asignacionOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (curso.isEmpty() || estudiante.isEmpty()) {
            return ResponseEntity.badRequest().body("Curso o estudiante no encontrado.");
        }

        AsignacionCurso asignacion = asignacionOpt.get();
        asignacion.setCurso(curso.get());
        asignacion.setEstudiante(estudiante.get());

        AsignacionCurso actualizada = asignacionCursoRepository.save(asignacion);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAsignacion(@PathVariable Integer id) {
        if (!asignacionCursoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        asignacionCursoRepository.deleteById(id);
        return ResponseEntity.ok("Asignación eliminada con éxito.");
    }

}
