package com.company.ForoHub.Topico;

import java.time.LocalDate;

public record MostrarTopicos(
        String titulo,
        String mensaje,
        LocalDate fechaCreacion,
        String status,
        String persona,
        String curso
) {
    public MostrarTopicos(Topico topico){
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion().toLocalDate(),
                String.valueOf(topico.getStatus()),topico.getPersona(),topico.getCurso());
    }
}
