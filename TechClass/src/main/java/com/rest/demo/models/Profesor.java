package com.rest.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Profesor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int idProfesor;
	private String name;
	private int phone;
	private String mail;
	private String clave;
	
	public Profesor() {
		// TODO Auto-generated constructor stub
	}

	public Profesor(int idProfesor, String name, int phone, String mail, String clave) {
		super();
		this.idProfesor = idProfesor;
		this.name = name;
		this.phone = phone;
		this.mail = mail;
		this.clave = clave;
	}

	public int getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(int idProfesor) {
		this.idProfesor = idProfesor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
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
