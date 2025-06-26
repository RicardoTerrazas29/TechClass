package com.rest.demo.dto;

public class LogroEstudianteDTO {
    private Integer id;
    private Integer idEstudiante;
    private Integer idLogro;

    public LogroEstudianteDTO() {
    }

    public LogroEstudianteDTO(Integer id, Integer idEstudiante, Integer idLogro) {
        this.id = id;
        this.idEstudiante = idEstudiante;
        this.idLogro = idLogro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Integer getIdLogro() {
        return idLogro;
    }

    public void setIdLogro(Integer idLogro) {
        this.idLogro = idLogro;
    }
}
