package com.rest.demo.dto;

import java.sql.Timestamp;

public class PublicacionDTO {
    public Integer idPublicacion;
    public String titulo;
    public String contenido;
    public Timestamp fecha;
    public String autor;
    
    public PublicacionDTO() {
		// TODO Auto-generated constructor stub
	}

	public PublicacionDTO(Integer idPublicacion, String titulo, String contenido, Timestamp fecha, String autor) {
		super();
		this.idPublicacion = idPublicacion;
		this.titulo = titulo;
		this.contenido = contenido;
		this.fecha = fecha;
		this.autor = autor;
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
    
    
}