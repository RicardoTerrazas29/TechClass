package com.rest.demo.service;
import com.rest.demo.models.Curso;
import com.rest.demo.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso obtenerCursoPorId(int id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public List<Curso> obtenerTodosLosCursos() {
        return cursoRepository.findAll();
    }

    public void guardarCurso(Curso curso) {
        cursoRepository.save(curso);
    }

    public void actualizarCurso(int id, Curso cursoActualizado) {
        Optional<Curso> cursoExistente = cursoRepository.findById(id);
        if (cursoExistente.isPresent()) {
            Curso curso = cursoExistente.get();
            curso.setNombre(cursoActualizado.getNombre());
            curso.setDescripcion(cursoActualizado.getDescripcion());
            cursoRepository.save(curso);
        } else {
            throw new RuntimeException("Curso no encontrado con id: " + id);
        }
    }

    public void eliminarCurso(int id) {
        cursoRepository.deleteById(id);
    }
}