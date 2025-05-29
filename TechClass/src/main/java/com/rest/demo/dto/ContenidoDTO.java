package com.rest.demo.dto;

public class ContenidoDTO {

    private Integer idCurso;
    private String titulo;
    private String descripcion;
    private String tipoContenido;
    private String urlContenido;

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
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
}

