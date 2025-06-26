package com.rest.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "logro")
public class Logro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLogro;

    private String titulo;
    private String icono; // ruta de la imagen

    @ManyToOne
    @JoinColumn(name = "idCurso")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "idContenido")
    private Contenido contenido;

    // Getters y setters
    public Integer getIdLogro() { return idLogro; }
    public void setIdLogro(Integer idLogro) { this.idLogro = idLogro; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getIcono() { return icono; }
    public void setIcono(String icono) { this.icono = icono; }
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
    public Contenido getContenido() { return contenido; }
    public void setContenido(Contenido contenido) { this.contenido = contenido; }
}
