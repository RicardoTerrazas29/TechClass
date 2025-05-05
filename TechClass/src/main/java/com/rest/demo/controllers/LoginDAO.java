package com.rest.demo.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.demo.models.Administrador;
import com.rest.demo.models.Estudiante;
import com.rest.demo.models.Profesor;
import com.rest.demo.repository.AdministradorRepository;
import com.rest.demo.repository.EstudianteRepository;
import com.rest.demo.repository.ProfesorRepository;

@RestController
@RequestMapping("/auth")
public class LoginDAO {

    @Autowired
    private AdministradorRepository adminRepo;
    @Autowired
    private ProfesorRepository profRepo;
    @Autowired
    private EstudianteRepository estRepo;
    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {
        String email = login.getEmail();
        String password = login.getPassword();

        // Administrador
        Optional<Administrador> admin = adminRepo.findByMail(email);
        if (admin.isPresent() && encoder.matches(password, admin.get().getClave())) {
            return ResponseEntity.ok(
                java.util.Map.of("role", "ADMIN", "name", admin.get().getName())
            );
        }

     // Profesor
        Optional<Profesor> prof = profRepo.findByMail(email);
        if (prof.isPresent() && encoder.matches(password, prof.get().getClave())) {
            Profesor p = prof.get();
            return ResponseEntity.ok(
            	    Map.of(
            	        "role", "PROFESOR",
            	        "name", p.getName(),
            	        "phone", String.valueOf(p.getPhone()),
            	        "mail", p.getMail(),
            	        "idProfesor", String.valueOf(p.getIdProfesor()) // <-- ESTO VIENE DEL BACKEND
            	    )
            	);

        }


        // Estudiante
     // Estudiante
        Optional<Estudiante> est = estRepo.findByMail(email);
        if (est.isPresent() && encoder.matches(password, est.get().getClave())) {
            Estudiante e = est.get();
            return ResponseEntity.ok(
                Map.of(
                    "role", "ESTUDIANTE",
                    "name", e.getName(),
                    "idEstudiante", String.valueOf(e.getIdEstudiante()) // Aquí añadimos el idEstudiante
                )
            );
        }


        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }


    // Clase auxiliar para recibir JSON
    public static class LoginRequest {
        private String email;
        private String password;
        // Getters y setters
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}