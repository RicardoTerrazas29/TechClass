package com.rest.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Estudiante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int idEstudiante;
	private String name;
	private String dni;
	private String genero;
	private String address;
	private String mail;
	private String clave;
	
	public Estudiante() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Estudiante(int idEstudiante, String name, String dni, String genero, String address, String mail,
			String clave) {
		super();
		this.idEstudiante = idEstudiante;
		this.name = name;
		this.dni = dni;
		this.genero = genero;
		this.address = address;
		this.mail = mail;
		this.clave = clave;
	}



	public int getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(int idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	
}
