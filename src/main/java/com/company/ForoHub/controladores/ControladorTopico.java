package com.company.ForoHub.controladores;

import com.company.ForoHub.Topico.DatosRegistroTopico;
import com.company.ForoHub.Topico.MostrarTopicos;
import com.company.ForoHub.Topico.RespuestaTopico;
import com.company.ForoHub.Topico.Topico;
import com.company.ForoHub.repositorio.TopicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.print.Pageable;
import java.net.URI;

@RestController
@RequestMapping(path="/topico")
public class ControladorTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<RespuestaTopico> registrarTopico(@RequestBody DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
        RespuestaTopico respuestaTopico = new RespuestaTopico(topico.getTitulo(),topico.getMensaje(),topico.getPersona(),
                topico.getCurso());
        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(respuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<MostrarTopicos>> mostrarTopicos(@PageableDefault(size=5)Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(MostrarTopicos::new));
    }

    @GetMapping(path="/{id")
    public ResponseEntity<MostrarTopicos> topicoPorId(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new MostrarTopicos(topico.getTitulo(),topico.getMensaje(), topico.getFechaCreacion().toLocalDate(),
                topico.getStatus().toString(), topico.getPersona(), topico.getCurso());
        return ResponseEntity.ok(datosTopico);
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity topicoEliminado(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.eliminarTopico();
        return ResponseEntity.noContent().build();
    }
}
