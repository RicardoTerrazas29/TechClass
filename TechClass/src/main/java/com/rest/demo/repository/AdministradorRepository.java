package com.rest.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.demo.models.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer>{
	Optional<Administrador> findByMail(String mail);
}
