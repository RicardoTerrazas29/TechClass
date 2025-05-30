package com.rest.demo.dto;

import java.sql.Timestamp;

public class ComentarioDTO {
    public Integer idComentario;
    public String comentario;
    public Timestamp fecha;
    public String autor;
    
    public ComentarioDTO() {
		// TODO Auto-generated constructor stub
	}

	public ComentarioDTO(Integer idComentario, String comentario, Timestamp fecha, String autor) {
		super();
		this.idComentario = idComentario;
		this.comentario = comentario;
		this.fecha = fecha;
		this.autor = autor;
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
    
    
}