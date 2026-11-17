package com.example.demo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "eventos")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String resumen;
    @Column(length = 1000)
    private String descripcion;
    private String cupos;
    private String cuando;
    private String participantes;
    private String horario;
    private String fecha;
    private double latitud;
    private double longitud;

    public Evento() {}
    public Evento(String titulo, String resumen, String descripcion, String cupos,
                  String cuando, String participantes, String horario, String fecha,
                  double latitud, double longitud) {
        this.titulo = titulo; this.resumen = resumen; this.descripcion = descripcion;
        this.cupos = cupos; this.cuando = cuando; this.participantes = participantes;
        this.horario = horario; this.fecha = fecha; this.latitud = latitud; this.longitud = longitud;
    }
    // Agrega todos los Getters y Setters correspondientes
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getResumen() { return resumen; }
    public void setResumen(String resumen) { this.resumen = resumen; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getCupos() { return cupos; }
    public void setCupos(String cupos) { this.cupos = cupos; }
    public String getCuando() { return cuando; }
    public void setCuando(String cuando) { this.cuando = cuando; }
    public String getParticipantes() { return participantes; }
    public void setParticipantes(String participantes) { this.participantes = participantes; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public double getLatitud() { return latitud; }
    public void setLatitud(double latitud) { this.latitud = latitud; }
    public double getLongitud() { return longitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }
}
