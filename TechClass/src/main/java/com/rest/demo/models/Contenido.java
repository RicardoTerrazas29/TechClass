package com.rest.demo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "contenido")
public class Contenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContenido;

    @ManyToOne
    @JoinColumn(name = "idCurso", nullable = false)
    private Curso curso;
    private String titulo;
    private String descripcion;
    private String tipoContenido;
    private String urlContenido;

    @OneToMany(mappedBy = "contenido",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Recurso> recursos = new ArrayList<>();

    public Contenido() {
		// TODO Auto-generated constructor stub
	}

    public Contenido(Integer idContenido, Curso curso, String titulo, String descripcion, String tipoContenido,
			String urlContenido) {
		super();
		this.idContenido = idContenido;
		this.curso = curso;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.tipoContenido = tipoContenido;
		this.urlContenido = urlContenido;
	}


	public Integer getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(Integer idContenido) {
        this.idContenido = idContenido;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoContenido() {
        return tipoContenido;
    }

    public void setTipoContenido(String tipoContenido) {
        this.tipoContenido = tipoContenido;
    }

    public String getUrlContenido() {
        return urlContenido;
    }

    public void setUrlContenido(String urlContenido) {
        this.urlContenido = urlContenido;
    }

    public List<Recurso> getRecursos(){return recursos; }
    public void setRecursos(List<Recurso> recursos){this.recursos=recursos;}
}


