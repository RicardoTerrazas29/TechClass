package com.rest.demo.service;

import com.rest.demo.models.Administrador;
import com.rest.demo.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminstradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

   
    public List<Administrador> listarTodos() {
        return administradorRepository.findAll();
    }


    public Administrador buscarPorId(int id) {
        Optional<Administrador> admin = administradorRepository.findById(id);
        return admin.orElse(null);
    }

   
    public void guardar(Administrador administrador) {
        administradorRepository.save(administrador);
    }

  
    public void eliminar(int id) {
        administradorRepository.deleteById(id);
    }
}