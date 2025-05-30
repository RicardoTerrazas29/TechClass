package com.rest.demo.controllers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rest.demo.dto.ComentarioDTO;
import com.rest.demo.dto.PublicacionDTO;
import com.rest.demo.models.Estudiante;
import com.rest.demo.models.ForoComentario;
import com.rest.demo.models.ForoPublicacion;
import com.rest.demo.repository.EstudianteRepository;
import com.rest.demo.repository.ForoComentarioRepository;
import com.rest.demo.repository.ForoPublicacionRepository;

@RestController
@RequestMapping("/api/foro")
public class ForoController {

	@Autowired
    private ForoPublicacionRepository publicacionRepo;
	
	@Autowired
    private ForoComentarioRepository comentarioRepo;
	
	@Autowired
    private EstudianteRepository estudianteRepo;

    // ✅ Listar publicaciones
    @GetMapping("/publicaciones")
    public List<PublicacionDTO> listarPublicaciones() {
        return publicacionRepo.findAll().stream().map(p -> {
            PublicacionDTO dto = new PublicacionDTO();
            dto.idPublicacion = p.getIdPublicacion();
            dto.titulo = p.getTitulo();
            dto.contenido = p.getContenido();
            dto.fecha = p.getFecha();
            dto.autor = p.getEstudiante().getName();
            return dto;
        }).collect(Collectors.toList());
    }
    
	 // ✅ Listar todos los comentarios
    @GetMapping("/comentarios")
    public List<ComentarioDTO> listarTodosLosComentarios() {
        return comentarioRepo.findAll().stream().map(c -> {
            ComentarioDTO dto = new ComentarioDTO();
            dto.idComentario = c.getIdComentario();
            dto.comentario = c.getComentario();
            dto.fecha = c.getFecha();
            dto.autor = c.getEstudiante().getName();
            return dto;
        }).collect(Collectors.toList());
    }

    // ✅ Listar comentarios de una publicación
    @GetMapping("/publicaciones/{id}/comentarios")
    public List<ComentarioDTO> listarComentarios(@PathVariable Integer id) {
        return comentarioRepo.findAll().stream()
                .filter(c -> c.getPublicacion().getIdPublicacion().equals(id))
                .map(c -> {
                    ComentarioDTO dto = new ComentarioDTO();
                    dto.idComentario = c.getIdComentario();
                    dto.comentario = c.getComentario();
                    dto.fecha = c.getFecha();
                    dto.autor = c.getEstudiante().getName();
                    return dto;
                }).collect(Collectors.toList());
    }

    // ✅ Crear publicación
    @PostMapping("/publicaciones")
    public ResponseEntity<?> crearPublicacion(@RequestBody Map<String, Object> payload) {
        Integer idEstudiante = (Integer) payload.get("idEstudiante");
        String titulo = (String) payload.get("titulo");
        String contenido = (String) payload.get("contenido");

        Estudiante est = estudianteRepo.findById(idEstudiante)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        ForoPublicacion pub = new ForoPublicacion();
        pub.setTitulo(titulo);
        pub.setContenido(contenido);
        pub.setEstudiante(est);
        pub.setFecha(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(publicacionRepo.save(pub));
    }


    // ✅ comentario en una publicación
    @PostMapping("/comentarios")
    public ResponseEntity<?> crearComentario(@RequestBody Map<String, Object> payload) {
        Integer idEstudiante = (Integer) payload.get("idEstudiante");
        Integer idPublicacion = (Integer) payload.get("idPublicacion");
        String comentarioTexto = (String) payload.get("comentario");

        Estudiante est = estudianteRepo.findById(idEstudiante)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        ForoPublicacion pub = publicacionRepo.findById(idPublicacion)
            .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        ForoComentario comentario = new ForoComentario();
        comentario.setComentario(comentarioTexto);
        comentario.setFecha(new Timestamp(System.currentTimeMillis()));
        comentario.setEstudiante(est);
        comentario.setPublicacion(pub);

        return ResponseEntity.ok(comentarioRepo.save(comentario));
    }
}

