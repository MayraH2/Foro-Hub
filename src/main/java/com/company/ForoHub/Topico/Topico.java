package com.company.ForoHub.Topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name="Topico")
@Table(name="topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.NORESPONDIDO;

    private String persona;
    private String curso;
    private Boolean activo;

    public Topico(DatosRegistroTopico datosRegistroTopico){
        this.activo = true;
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.persona = datosRegistroTopico.persona();
        this.curso = datosRegistroTopico.curso();
    }

    public void actualizarDatos(ActualizarDatosTopico actualizarDatos){
        if(actualizarDatos.titulo() != null){
            this.titulo = actualizarDatos.titulo();
        }

        if(actualizarDatos.mensaje() != null){
            this.mensaje = actualizarDatos.mensaje();
        }

        if(actualizarDatos.persona() != null){
            this.persona = actualizarDatos.persona();
        }

        if(actualizarDatos.curso() != null){
            this.curso = actualizarDatos.curso();
        }
    }

    public void eliminarTopico(){
        this.activo = false;
        this.status = StatusTopico.ACLARADO;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public String getPersona() {
        return persona;
    }

    public String getCurso() {
        return curso;
    }

    public Long getId() {
        return id;
    }


}
