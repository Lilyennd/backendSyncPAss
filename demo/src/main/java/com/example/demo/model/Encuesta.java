package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "encuestas")
public class Encuesta {
    @Id
    private String id;
    private String actividad;
    private int estrellasInstructor;
    private int satisfaccionAyudas;
    private int tratoVoluntarios;
    private String recomendaria;
    private long timestamp;

    public Encuesta() {}
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getActividad() { return actividad; }
    public void setActividad(String actividad) { this.actividad = actividad; }
    public int getEstrellasInstructor() { return estrellasInstructor; }
    public void setEstrellasInstructor(int estrellasInstructor) { this.estrellasInstructor = estrellasInstructor; }
    public int getSatisfaccionAyudas() { return satisfaccionAyudas; }
    public void setSatisfaccionAyudas(int satisfaccionAyudas) { this.satisfaccionAyudas = satisfaccionAyudas; }
    public int getTratoVoluntarios() { return tratoVoluntarios; }
    public void setTratoVoluntarios(int tratoVoluntarios) { this.tratoVoluntarios = tratoVoluntarios; }
    public String getRecomendaria() { return recomendaria; }
    public void setRecomendaria(String recomendaria) { this.recomendaria = recomendaria; }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
