package com.rest.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "logro_estudiante")
public class LogroEstudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idEstudiante")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "idLogro")
    private Logro logro;

    // Getters y setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
    public Logro getLogro() { return logro; }
    public void setLogro(Logro logro) { this.logro = logro; }
}
