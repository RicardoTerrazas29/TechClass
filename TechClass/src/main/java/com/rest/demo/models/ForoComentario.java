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
@Table(name = "foro_comentario")
public class ForoComentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComentario;

    private String comentario;
    private Timestamp fecha;

    @ManyToOne
    @JoinColumn(name = "idPublicacion")
    private ForoPublicacion publicacion;

    @ManyToOne
    @JoinColumn(name = "idEstudiante")
    private Estudiante estudiante;
    
    public ForoComentario() {
		// TODO Auto-generated constructor stub
	}

	public ForoComentario(Integer idComentario, String comentario, Timestamp fecha, ForoPublicacion publicacion,
			Estudiante estudiante) {
		super();
		this.idComentario = idComentario;
		this.comentario = comentario;
		this.fecha = fecha;
		this.publicacion = publicacion;
		this.estudiante = estudiante;
	}

	public Integer getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(Integer idComentario) {
		this.idComentario = idComentario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public ForoPublicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(ForoPublicacion publicacion) {
		this.publicacion = publicacion;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
    
    
}