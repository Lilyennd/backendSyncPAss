package com.example.demo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "asistencias")
public class Asistencia {
    @Id
    private String id;
    private Long eventoId;
    private String codigoAsistente;
    private long timestamp;
    private Double latitud;
    private Double longitud;
    private String validacion;

    public Asistencia() {}
    // Agrega todos los Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Long getEventoId() { return eventoId; }
    public void setEventoId(Long eventoId) { this.eventoId = eventoId; }
    public String getCodigoAsistente() { return codigoAsistente; }
    public void setCodigoAsistente(String codigoAsistente) { this.codigoAsistente = codigoAsistente; }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    public Double getLatitud() { return latitud; }
    public void setLatitud(Double latitud) { this.latitud = latitud; }
    public Double getLongitud() { return longitud; }
    public void setLongitud(Double longitud) { this.longitud = longitud; }
    public String getValidacion() { return validacion; }
    public void setValidacion(String validacion) { this.validacion = validacion; }
}
