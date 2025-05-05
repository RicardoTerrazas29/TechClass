package com.rest.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.rest.demo.dto.CursoDTO;
import com.rest.demo.models.Curso;
import com.rest.demo.models.Profesor;
import com.rest.demo.repository.CursoRepository;
import com.rest.demo.repository.ProfesorRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class CursoDAO {

    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private ProfesorRepository profesorRepository;

    // Crear Curso
    @PostMapping
    public ResponseEntity<Curso> createCurso(@RequestParam String nombre, 
                                             @RequestParam String descripcion, 
                                             @RequestParam(required = false) MultipartFile foto, 
                                             @RequestParam Integer idProfesor) throws IOException {
        if (idProfesor == null || idProfesor < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // ID del profesor no válido
        }

        Optional<Profesor> profesor = profesorRepository.findById(idProfesor);
        if (!profesor.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // Profesor no encontrado
        }

        Curso nuevoCurso = new Curso();
        nuevoCurso.setNombre(nombre);
        nuevoCurso.setDescripcion(descripcion);
        nuevoCurso.setProfesor(profesor.get());

        // Guardar imagen si existe
        if (foto != null && !foto.isEmpty()) {
            String photoUrl = saveFile(foto);  // Guardar la foto en el servidor
            nuevoCurso.setFoto(photoUrl); // Ruta de la imagen guardada
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(cursoRepository.save(nuevoCurso));
    }

    @GetMapping
    public ResponseEntity<List<CursoDTO>> getCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        List<CursoDTO> cursosDTO = cursos.stream()
            .map(CursoDTO::new)
            .toList();
        return ResponseEntity.ok(cursosDTO);
    }



    // Actualizar Curso
    @PutMapping("/{id}")
    public Curso actualizarCurso(@PathVariable Integer id, 
                                 @RequestParam("nombre") String nombre,
                                 @RequestParam("descripcion") String descripcion,
                                 @RequestParam(value = "foto", required = false) MultipartFile foto,
                                 @RequestParam("idProfesor") Integer idProfesor) throws IOException {

        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        curso.setNombre(nombre);  // <-- ¡ESTO es clave!
        curso.setDescripcion(descripcion);

        if (foto != null && !foto.isEmpty()) {
            String ruta = "uploads/" + foto.getOriginalFilename();
            foto.transferTo(new File(ruta));
            curso.setFoto(ruta);
        }

        Profesor profesor = profesorRepository.findById(idProfesor)
                                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        curso.setProfesor(profesor);

        return cursoRepository.save(curso);
    }


    // Eliminar Curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Integer id) {
        Optional<Curso> existingCurso = cursoRepository.findById(id);
        if (!existingCurso.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Curso no encontrado
        }
        cursoRepository.deleteById(id);
        return ResponseEntity.noContent().build();  // Respuesta exitosa sin contenido
    }

    // Guardar archivo de foto
    private String saveFile(MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String uploadDir = System.getProperty("user.dir") + "/imagenes-guardar/";

        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        File dest = new File(uploadDir + fileName);
        file.transferTo(dest);

        return "imagenes-guardar/" + fileName;  // Ruta para la imagen guardada
    }
}
