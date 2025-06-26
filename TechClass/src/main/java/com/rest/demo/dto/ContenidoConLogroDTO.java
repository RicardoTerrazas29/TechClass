package com.rest.demo.dto;

import java.util.List;

public class ContenidoConLogroDTO {
    private Integer idContenido;
    private String titulo;
    private String descripcion;
    private String tipoContenido;
    private String urlContenido;
    private List<RecursoDTO> recursos;
    private Integer idLogro;

    public ContenidoConLogroDTO() {}

    public ContenidoConLogroDTO(Integer idContenido, String titulo, String descripcion, String tipoContenido, String urlContenido, List<RecursoDTO> recursos, Integer idLogro) {
        this.idContenido = idContenido;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipoContenido = tipoContenido;
        this.urlContenido = urlContenido;
        this.recursos = recursos;
        this.idLogro = idLogro;
    }

    public Integer getIdContenido() { return idContenido; }
    public void setIdContenido(Integer idContenido) { this.idContenido = idContenido; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getTipoContenido() { return tipoContenido; }
    public void setTipoContenido(String tipoContenido) { this.tipoContenido = tipoContenido; }
    public String getUrlContenido() { return urlContenido; }
    public void setUrlContenido(String urlContenido) { this.urlContenido = urlContenido; }
    public List<RecursoDTO> getRecursos() { return recursos; }
    public void setRecursos(List<RecursoDTO> recursos) { this.recursos = recursos; }
    public Integer getIdLogro() { return idLogro; }
    public void setIdLogro(Integer idLogro) { this.idLogro = idLogro; }
} 