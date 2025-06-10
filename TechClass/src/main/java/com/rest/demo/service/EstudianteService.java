package com.rest.demo.service;

import com.rest.demo.models.Estudiante;
import com.rest.demo.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodos() {
        return estudianteRepository.findAll();
    }

    public Estudiante buscarPorId(int id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + id));
    }

    public void guardar(Estudiante estudiante) {
        estudianteRepository.save(estudiante);
    }

    public void actualizar(int id, Estudiante estudianteActualizado) {
        Estudiante estudianteExistente = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + id));

        estudianteExistente.setName(estudianteActualizado.getName());
        estudianteExistente.setDni(estudianteActualizado.getDni());
        estudianteExistente.setGenero(estudianteActualizado.getGenero());
        estudianteExistente.setAddress(estudianteActualizado.getAddress());
        estudianteExistente.setMail(estudianteActualizado.getMail());
        estudianteExistente.setClave(estudianteActualizado.getClave());

        estudianteRepository.save(estudianteExistente);
    }

    public void eliminar(int id) {
        estudianteRepository.deleteById(id);
    }
}