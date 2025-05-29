package com.rest.demo.controllers;

import com.rest.demo.dto.ContenidoDTO;
import com.rest.demo.models.Contenido;
import com.rest.demo.models.Curso;
import com.rest.demo.repository.ContenidoRepository;
import com.rest.demo.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contenidos")
public class ContenidoDAO {

    @Autowired
    private ContenidoRepository contenidoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<List<Contenido>> listarContenidos() {
        List<Contenido> contenidos = contenidoRepository.findAll();
        return ResponseEntity.ok(contenidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerContenido(@PathVariable Integer id) {
        Optional<Contenido> contenidoOpt = contenidoRepository.findById(id);
        if (!contenidoOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contenidoOpt.get());
    }
    
    @GetMapping("/curso/{idCurso}")
    public ResponseEntity<List<Contenido>> obtenerContenidosPorCurso(@PathVariable Integer idCurso) {
        List<Contenido> contenidos = contenidoRepository.findByCursoIdCurso(idCurso);
        return ResponseEntity.ok(contenidos);
    }

    @PostMapping
    public ResponseEntity<?> crearContenido(@Validated @RequestBody ContenidoDTO contenidoDTO) {
        Optional<Curso> cursoOpt = cursoRepository.findById(contenidoDTO.getIdCurso());
        if (!cursoOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Curso no encontrado con id: " + contenidoDTO.getIdCurso());
        }

        Contenido contenido = new Contenido();
        contenido.setCurso(cursoOpt.get());
        contenido.setTitulo(contenidoDTO.getTitulo());
        contenido.setDescripcion(contenidoDTO.getDescripcion());
        contenido.setTipoContenido(contenidoDTO.getTipoContenido());
        contenido.setUrlContenido(contenidoDTO.getUrlContenido());

        contenidoRepository.save(contenido);

        return ResponseEntity.ok(contenido);
    }

    // PUT: actualizar contenido por id
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarContenido(@PathVariable Integer id, @Validated @RequestBody ContenidoDTO contenidoDTO) {
        Optional<Contenido> contenidoOpt = contenidoRepository.findById(id);
        if (!contenidoOpt.isPresent()) {
            return ResponseEntity.notFound().build();

        }

        Optional<Curso> cursoOpt = cursoRepository.findById(contenidoDTO.getIdCurso());
        if (!cursoOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Curso no encontrado con id: " + contenidoDTO.getIdCurso());
        }

        Contenido contenido = contenidoOpt.get();
        contenido.setCurso(cursoOpt.get());
        contenido.setTitulo(contenidoDTO.getTitulo());
        contenido.setDescripcion(contenidoDTO.getDescripcion());
        contenido.setTipoContenido(contenidoDTO.getTipoContenido());
        contenido.setUrlContenido(contenidoDTO.getUrlContenido());

        contenidoRepository.save(contenido);

        return ResponseEntity.ok(contenido);
    }

    // DELETE: eliminar contenido por id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarContenido(@PathVariable Integer id) {
        Optional<Contenido> contenidoOpt = contenidoRepository.findById(id);
        if (!contenidoOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        contenidoRepository.deleteById(id);
        return ResponseEntity.ok().body("Contenido eliminado con id: " + id);
    }
}

