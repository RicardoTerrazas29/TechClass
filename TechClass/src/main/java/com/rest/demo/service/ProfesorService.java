package com.rest.demo.service;

import com.rest.demo.models.Profesor;
import com.rest.demo.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    public List<Profesor> listarTodos() {
        return profesorRepository.findAll();
    }

    public Profesor buscarPorId(int id) {
        return profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado con id: " + id));
    }

    public void guardar(Profesor profesor) {
        profesorRepository.save(profesor);
    }

    public void actualizar(int id, Profesor profesorActualizado) {
        Profesor profesorExistente = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado con id: " + id));

        profesorExistente.setName(profesorActualizado.getName());
        profesorExistente.setPhone(profesorActualizado.getPhone());
        profesorExistente.setMail(profesorActualizado.getMail());
        profesorExistente.setClave(profesorActualizado.getClave());

        profesorRepository.save(profesorExistente);
    }

    public void eliminar(int id) {
        profesorRepository.deleteById(id);
    }
}