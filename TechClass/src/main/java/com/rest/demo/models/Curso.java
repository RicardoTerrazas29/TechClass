package com.rest.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCurso;
    private String nombre;
    private String descripcion;
    private String foto;

    // Relación ManyToOne con Profesor
    @ManyToOne
    @JoinColumn(name = "idProfesorCurso")
    private Profesor profesor;

    public Curso() {
    }

    public Curso(Integer idCurso, String nombre, String descripcion, String foto, Profesor profesor) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.profesor = profesor;
    }

    // Getters y setters
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

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
