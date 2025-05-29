package com.rest.demo.dto;

public class AsignacionCursoDTO {
    private Integer idCurso;
    private Integer idEstudiante;
    
	public AsignacionCursoDTO(Integer idCurso, Integer idEstudiante) {
		super();
		this.idCurso = idCurso;
		this.idEstudiante = idEstudiante;
	}

	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	public Integer getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(Integer idEstudiante) {
		this.idEstudiante = idEstudiante;
	}
    
    
}