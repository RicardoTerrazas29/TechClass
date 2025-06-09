package com.rest.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "recurso")
public class Recurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRecurso;
    private String nombre;
    private String tipoContenido;
    private String url;

    @ManyToOne
    @JoinColumn(name = "idContenido")
    @JsonIgnore
    private Contenido contenido;

    public Integer getIdRecurso() {return idRecurso;}
    public void setIdRecurso(Integer idRecurso){this.idRecurso = idRecurso;}
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipoContenido () { return tipoContenido; }
    public void setTipoContenido(String tipoContenido) { this.tipoContenido = tipoContenido; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public Contenido getContenido() { return contenido; }
    public void setContenido(Contenido contenido) { this.contenido = contenido; }
}
