package com.rest.demo.models;


import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "foro_publicacion")
public class ForoPublicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPublicacion;

    private String titulo;
    private String contenido;
    private Timestamp fecha;

    @ManyToOne
    @JoinColumn(name = "idEstudiante")
    private Estudiante estudiante;
    
    public ForoPublicacion() {
		// TODO Auto-generated constructor stub
	}

	public ForoPublicacion(Integer idPublicacion, String titulo, String contenido, Timestamp fecha,
			Estudiante estudiante) {
		super();
		this.idPublicacion = idPublicacion;
		this.titulo = titulo;
		this.contenido = contenido;
		this.fecha = fecha;
		this.estudiante = estudiante;
	}

	public Integer getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(Integer idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
    
    
}
