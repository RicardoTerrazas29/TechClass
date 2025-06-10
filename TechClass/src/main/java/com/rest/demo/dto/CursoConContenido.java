package com.rest.demo.dto;

import java.util.List;

import com.rest.demo.models.Contenido;

public class CursoConContenido {
    private String nombreCurso;
    private List<Contenido> contenidos;

    public CursoConContenido(String nombreCurso, List<Contenido> contenidos) {
        this.nombreCurso = nombreCurso;
        this.contenidos = contenidos;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public List<Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(List<Contenido> contenidos) {
        this.contenidos = contenidos;
    }
}
