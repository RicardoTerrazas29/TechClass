package com.rest.demo.controllers;

import com.rest.demo.dto.ContenidoCompletadoDTO;
import com.rest.demo.dto.ContenidoDTO;
import com.rest.demo.dto.CursoConContenido;
import com.rest.demo.dto.RecursoDTO;
import com.rest.demo.dto.RecursoRevisadoDTO;
import com.rest.demo.models.Contenido;
import com.rest.demo.models.ContenidoCompletado;
import com.rest.demo.models.Curso;
import com.rest.demo.models.Estudiante;
import com.rest.demo.models.Recurso;
import com.rest.demo.models.RecursoRevisado;
import com.rest.demo.repository.ContenidoCompletadoRepository;
import com.rest.demo.repository.ContenidoRepository;
import com.rest.demo.repository.CursoRepository;
import com.rest.demo.repository.EstudianteRepository;
import com.rest.demo.repository.RecursoRepository;
import com.rest.demo.repository.RecursoRevisadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contenidos")
public class ContenidoDAO {

    @Autowired
    private ContenidoRepository contenidoRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private RecursoRepository recursoRepository;

    @Autowired
    private RecursoRevisadoRepository recursoRevisadoRepository;

    @Autowired
    private ContenidoCompletadoRepository contenidoCompletadoRepository;
    
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<List<Contenido>> listarContenidos() {
        List<Contenido> contenidos = contenidoRepository.findAll();
        return ResponseEntity.ok(contenidos);
    }
    // GET: obtener contenido por id
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerContenido(@PathVariable Integer id) {
        Optional<Contenido> contenidoOpt = contenidoRepository.findById(id);
        if (!contenidoOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contenidoOpt.get());
    }
    // GET: obtener contenidos por curso
    @GetMapping("/curso/{idCurso}")
    public ResponseEntity<?> obtenerContenidosPorCurso(@PathVariable Integer idCurso) {
        Optional<Curso> cursoOpt = cursoRepository.findById(idCurso);
        if (!cursoOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Curso no encontrado con id: " + idCurso);
        }
        Curso curso = cursoOpt.get();
        List<Contenido> contenidos = contenidoRepository.findByCursoIdCurso(idCurso);
        CursoConContenido respuesta = new CursoConContenido(curso.getNombre(), contenidos);
        return ResponseEntity.ok(respuesta);
    }
    // POST: crear nuevo contenido
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

        if (contenidoDTO.getRecursos() != null) {
            List<Recurso> recursos = new ArrayList<>();
            for (RecursoDTO recursoDTO : contenidoDTO.getRecursos()) {
                Recurso recurso = new Recurso();
                recurso.setNombre(recursoDTO.getNombre());
                recurso.setUrl(recursoDTO.getUrl());
                recurso.setTipoContenido(recursoDTO.getTipoContenido());
                recurso.setContenido(contenido);
                recursos.add(recurso);
            }
            contenido.setRecursos(recursos);
        }
        
        contenidoRepository.save(contenido);

        return ResponseEntity.ok(contenido);
    }

    @PostMapping("/{id}/recursos")
    public ResponseEntity<?> agregarRecursosAContenido(
        @PathVariable Integer id,
        @RequestBody List<RecursoDTO> recursosDTO){
        Optional<Contenido> contenidoOpt = contenidoRepository.findById(id);
        if (!contenidoOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Contenido contenido = contenidoOpt.get();
        List<Recurso> recursos = contenido.getRecursos();
        if (recursos == null) recursos = new ArrayList<>();

        for (RecursoDTO recursoDTO : recursosDTO) {
            Recurso recurso = new Recurso();
            recurso.setNombre(recursoDTO.getNombre());
            recurso.setUrl(recursoDTO.getUrl());
            recurso.setTipoContenido(recursoDTO.getTipoContenido());
            recurso.setContenido(contenido);
            recursos.add(recurso);
        }
       
        contenidoRepository.save(contenido);

        return ResponseEntity.ok(contenido.getRecursos());
    }

    @PutMapping("/{idContenido}/recursos/{idRecurso}")
    public ResponseEntity<?> actualizarRecursoDeContenido(
        @PathVariable Integer idContenido,
        @PathVariable Integer idRecurso,
        @RequestBody RecursoDTO recursoDTO
    ){
        Optional<Contenido> contenidoOpt = contenidoRepository.findById(idContenido);
        if (!contenidoOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Contenido contenido = contenidoOpt.get();
        List<Recurso> recursos = contenido.getRecursos();

        Optional<Recurso> recursoOpt = recursos.stream()
            .filter(r -> r.getIdRecurso().equals(idRecurso))
            .findFirst();
        if (!recursoOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Recurso no encontrado en este contenido.");
        }
        Recurso recurso = recursoOpt.get();
        recurso.setNombre(recursoDTO.getNombre());
        recurso.setUrl(recursoDTO.getUrl());
        recurso.setTipoContenido(recursoDTO.getTipoContenido());

        contenidoRepository.save(contenido);

        return ResponseEntity.ok(recurso);
    }
    
    
    @DeleteMapping("/{idContenido}/recursos/{idRecurso}")
    public ResponseEntity<?> eliminarRecursosDeContenido(
        @PathVariable Integer idContenido,
        @PathVariable Integer idRecurso
    ){
        Optional<Contenido> contenidoOpt = contenidoRepository.findById(idContenido);
        if (!contenidoOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Contenido contenido = contenidoOpt.get();
        List<Recurso> recursos = contenido.getRecursos();

        boolean removed = recursos.removeIf(recurso -> recurso.getIdRecurso().equals(idRecurso));
        if (!removed) {
            return ResponseEntity.badRequest().body("Recurso no encontrado en este contenido.");
        }
        contenido.setRecursos(recursos);
        contenidoRepository.save(contenido);
        return ResponseEntity.ok().body("Recurso eliminado correctamente");
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

    //marcar recurso como revisado
    @PostMapping("/recursos/{idEstudiante}/revisado")
    public ResponseEntity<?> marcarRecursoComoRevisado(
            @PathVariable Integer idEstudiante,
            @RequestBody RecursoRevisadoDTO dto) {

        Optional<Estudiante> estudianteOpt = estudianteRepository.findById(idEstudiante);
        Optional<Recurso> recursoOpt = recursoRepository.findById(dto.idRecurso);
        Optional<Contenido> contenidoOpt = contenidoRepository.findById(dto.idContenido);

        if (estudianteOpt.isEmpty() || recursoOpt.isEmpty() || contenidoOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Datos inválidos");
        }

        // Evitar duplicados
        if (recursoRevisadoRepository.existsByEstudianteIdEstudianteAndRecursoIdRecurso(idEstudiante, dto.idRecurso)) {
            return ResponseEntity.status(409).body("Ya fue marcado como revisado");
        }

        RecursoRevisado revisado = new RecursoRevisado();
        revisado.setEstudiante(estudianteOpt.get());
        revisado.setRecurso(recursoOpt.get());
        revisado.setContenido(contenidoOpt.get());

        recursoRevisadoRepository.save(revisado);

        return ResponseEntity.ok("Recurso marcado como revisado");
    }

    //marcar contendio como completado
    @PostMapping("/{idEstudiante}/completado")
    public ResponseEntity<?> marcarContenidoComoCompletado(
            @PathVariable Integer idEstudiante,
            @RequestBody(required = true) ContenidoCompletadoDTO dto) {

        Optional<Estudiante> estudianteOpt = estudianteRepository.findById(idEstudiante);
        Optional<Contenido> contenidoOpt = contenidoRepository.findById(dto.idContenido);

        if (estudianteOpt.isEmpty() || contenidoOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Datos inválidos");
        }

        // Verifica si ya está marcado como completado
        if (contenidoCompletadoRepository.existsByEstudianteIdEstudianteAndContenidoIdContenido(idEstudiante, dto.idContenido)) {
            return ResponseEntity.status(409).body("Ya fue marcado como completado");
        }

        // Verifica si todos los recursos del contenido están revisados por el estudiante
        List<Recurso> recursos = recursoRepository.findByContenidoIdContenido(dto.idContenido);
        boolean todosRevisados = recursos.stream().allMatch(recurso ->
            recursoRevisadoRepository.existsByEstudianteIdEstudianteAndRecursoIdRecurso(idEstudiante, recurso.getIdRecurso())
        );

        if (!todosRevisados) {
            return ResponseEntity.badRequest().body("No todos los recursos han sido revisados");
        }

        ContenidoCompletado completado = new ContenidoCompletado();
        completado.setEstudiante(estudianteOpt.get());
        completado.setContenido(contenidoOpt.get());

        contenidoCompletadoRepository.save(completado);

        return ResponseEntity.ok("Contenido marcado como completado");
    }
    
    //traer el progreso del estudiante en un curso
    @GetMapping("/estudiantes/{idEstudiante}/progreso/curso/{idCurso}")
    public ResponseEntity<?> obtenerProgresoEstudianteEnCurso(
            @PathVariable Integer idEstudiante,
            @PathVariable Integer idCurso) {

        // Obtener todos los contenidos del curso
        List<Contenido> contenidos = contenidoRepository.findByCursoIdCurso(idCurso);

        // Para cada contenido, obtener recursos revisados y si está completado
        List<Integer> idsContenidos = new ArrayList<>();
        List<Integer> idsCompletados = new ArrayList<>();
        java.util.Map<Integer, List<Integer>> recursosRevisadosPorContenido = new java.util.HashMap<>();

        for (Contenido contenido : contenidos) {
            idsContenidos.add(contenido.getIdContenido());

            // Recursos revisados
            List<RecursoRevisado> revisados = recursoRevisadoRepository
                    .findByEstudianteIdEstudianteAndContenidoIdContenido(idEstudiante, contenido.getIdContenido());
            List<Integer> idsRevisados = revisados.stream()
                    .map(r -> r.getRecurso().getIdRecurso())
                    .toList();
            recursosRevisadosPorContenido.put(contenido.getIdContenido(), idsRevisados);

            // Contenido completado
            boolean completado = contenidoCompletadoRepository
                    .existsByEstudianteIdEstudianteAndContenidoIdContenido(idEstudiante, contenido.getIdContenido());
            if (completado) {
                idsCompletados.add(contenido.getIdContenido());
            }
        }

        return ResponseEntity.ok(
            java.util.Map.of(
                "contenidos", idsContenidos,
                "revisados", recursosRevisadosPorContenido,
                "completados", idsCompletados
            )
        );
    }
}

