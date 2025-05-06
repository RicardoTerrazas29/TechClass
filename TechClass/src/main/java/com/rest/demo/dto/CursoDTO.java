package com.rest.demo.dto;

import com.rest.demo.models.Curso;

public class CursoDTO {
    private Integer idCurso;
    private String nombre;
    private String descripcion;
    private String foto;
    private String nombreProfesor;
    private Integer idProfesor;

    public CursoDTO(Curso curso) {
        this.idCurso = curso.getIdCurso();
        this.nombre = curso.getNombre();
        this.descripcion = curso.getDescripcion();
        this.foto = curso.getFoto();
        this.idProfesor = curso.getProfesor().getIdProfesor();
        this.nombreProfesor = curso.getProfesor().getName();
    }

	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNombreProfesor() {
		return nombreProfesor;
	}

	public void setNombreProfesor(String nombreProfesor) {
		this.nombreProfesor = nombreProfesor;
	}

	public Integer getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(Integer idProfesor) {
		this.idProfesor = idProfesor;
	}

    
}
