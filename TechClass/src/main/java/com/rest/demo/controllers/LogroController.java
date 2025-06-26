package com.rest.demo.controllers;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rest.demo.models.Contenido;
import com.rest.demo.models.Curso;
import com.rest.demo.models.Estudiante;
import com.rest.demo.models.Logro;
import com.rest.demo.models.LogroEstudiante;
import com.rest.demo.repository.ContenidoRepository;
import com.rest.demo.repository.CursoRepository;
import com.rest.demo.repository.EstudianteRepository;
import com.rest.demo.repository.LogroEstudianteRepository;
import com.rest.demo.repository.LogroRepository;

@RestController
@RequestMapping("/api/logros")
public class LogroController {
    @Autowired
    private LogroRepository logroRepository;
    @Autowired
    private LogroEstudianteRepository logroEstudianteRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private ContenidoRepository contenidoRepository;
    @Autowired
    private EstudianteRepository estudianteRepository;
    
    // 1. GET: traer todos los logros
    @GetMapping
    public List<Logro> getAllLogros() {
        if (logroRepository.count() == 0) {
            return List.of(); // Retorna una lista vacía si no hay logros
        }else {
            return logroRepository.findAll();
        }
    }

    // 2. POST: crear logro (con imagen)
    @PostMapping
    public ResponseEntity<?> crearLogro(
            @RequestParam String titulo,
            @RequestParam("icono") MultipartFile icono,
            @RequestParam Integer idCurso,
            @RequestParam Integer idContenido) {
        try {
            Optional<Curso> cursoOpt = cursoRepository.findById(idCurso);
            Optional<Contenido> contenidoOpt = contenidoRepository.findById(idContenido);
            if (cursoOpt.isEmpty() || contenidoOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Curso o contenido no encontrado");
            }
            // Guardar imagen
            String fileName = System.currentTimeMillis() + "_" + icono.getOriginalFilename();
            String uploadDir = System.getProperty("user.dir") + "/logros/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();
            File dest = new File(uploadDir + fileName);
            icono.transferTo(dest);

            Logro logro = new Logro();
            logro.setTitulo(titulo);
            logro.setIcono("logros/" + fileName);
            logro.setCurso(cursoOpt.get());
            logro.setContenido(contenidoOpt.get());
            return ResponseEntity.ok(logroRepository.save(logro));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al guardar logro");
        }
    }

    // 3. GET: logros de un estudiante
    @GetMapping("/estudiante/{idEstudiante}")
    public List<Logro> getLogrosByEstudiante(@PathVariable Integer idEstudiante) {
        return logroEstudianteRepository.findByEstudianteIdEstudiante(idEstudiante)
                .stream().map(LogroEstudiante::getLogro).collect(Collectors.toList());
    }

    // 4. PUT: actualizar logro
    @PutMapping("/{idLogro}")
    public ResponseEntity<?> actualizarLogro(
            @PathVariable Integer idLogro,
            @RequestParam String titulo,
            @RequestParam(value = "icono", required = false) MultipartFile icono,
            @RequestParam Integer idCurso,
            @RequestParam Integer idContenido) {
        Optional<Logro> logroOpt = logroRepository.findById(idLogro);
        if (logroOpt.isEmpty()) return ResponseEntity.notFound().build();
        Optional<Curso> cursoOpt = cursoRepository.findById(idCurso);
        Optional<Contenido> contenidoOpt = contenidoRepository.findById(idContenido);
        if (cursoOpt.isEmpty() || contenidoOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Curso o contenido no encontrado");
        }
        Logro logro = logroOpt.get();
        logro.setTitulo(titulo);
        if (icono != null && !icono.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + icono.getOriginalFilename();
                String uploadDir = System.getProperty("user.dir") + "/logros/";
                java.io.File dir = new java.io.File(uploadDir);
                if (!dir.exists()) dir.mkdirs();
                java.io.File dest = new java.io.File(uploadDir + fileName);
                icono.transferTo(dest);
                logro.setIcono("logros/" + fileName);
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body("Error al guardar imagen");
            }
        }
        logro.setCurso(cursoOpt.get());
        logro.setContenido(contenidoOpt.get());
        return ResponseEntity.ok(logroRepository.save(logro));
    }

    // 5. DELETE: eliminar logro
    @DeleteMapping("/{idLogro}")
    public ResponseEntity<?> eliminarLogro(@PathVariable Integer idLogro) {
        if (!logroRepository.existsById(idLogro)) {
            return ResponseEntity.notFound().build();
        }
        logroRepository.deleteById(idLogro);
        return ResponseEntity.ok("Logro eliminado correctamente");
    }

    // 6. GET: obtener logro por id
    @GetMapping("/{idLogro}")
    public ResponseEntity<?> getLogroById(@PathVariable Integer idLogro) {
        Optional<Logro> logroOpt = logroRepository.findById(idLogro);
        return logroOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 7. POST: asignar logro a estudiante
    @PostMapping("/{idEstudiante}/{idLogro}")
    public ResponseEntity<?> asignarLogroAEstudiante(@PathVariable Integer idEstudiante, @PathVariable Integer idLogro) {
        Optional<Estudiante> estOpt = estudianteRepository.findById(idEstudiante);
        Optional<Logro> logroOpt = logroRepository.findById(idLogro);
        if (estOpt.isEmpty() || logroOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Estudiante o logro no encontrado");
        }
        //verifica si ya existe logro para ese estudiante
        boolean yaAsignado = logroEstudianteRepository
            .findByEstudianteIdEstudiante(idEstudiante)
            .stream()
            .anyMatch(le-> le.getLogro().getIdLogro().equals(idLogro));
        if (yaAsignado) {
            return ResponseEntity.status(409).body("El logro ya fue asignado a este estudiante");
        }
        LogroEstudiante le = new LogroEstudiante();
        le.setEstudiante(estOpt.get());
        le.setLogro(logroOpt.get());
        return ResponseEntity.ok(logroEstudianteRepository.save(le));
    }

    // EXTRA: obtener todos los logros asignados a estudiantes
    @GetMapping("/asignados")
    public List<LogroEstudiante> getAllLogrosAsignados() {
        return logroEstudianteRepository.findAll();
    }
}
