package com.rest.demo.dto;

public class RecursoDTO {
    private String nombre;
    private String tipoContenido;
    private String url;

    public RecursoDTO() {
        // Constructor por defecto
    }
    public RecursoDTO(String nombre, String tipoContenido, String url) {
        this.nombre = nombre;
        this.tipoContenido = tipoContenido;
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTipoContenido() {
        return tipoContenido;
    }
    public void setTipoContenido(String tipoContenido) {
        this.tipoContenido = tipoContenido;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
