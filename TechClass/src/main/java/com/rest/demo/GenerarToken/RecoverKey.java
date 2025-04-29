package com.rest.demo.GenerarToken;

import com.rest.demo.api.MailService;
import com.rest.demo.models.Administrador;
import com.rest.demo.models.Estudiante;
import com.rest.demo.models.Profesor;
import com.rest.demo.repository.AdministradorRepository;
import com.rest.demo.repository.EstudianteRepository;
import com.rest.demo.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecoverKey {

    @Autowired
    private MailService mailService;

    @Autowired
    private AdministradorRepository adminRepo;

    @Autowired
    private EstudianteRepository estudianteRepo;

    @Autowired
    private ProfesorRepository profesorRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String recuperarPorCorreo(String email) {
        Optional<Administrador> admin = adminRepo.findByMail(email);
        Optional<Profesor> profesor = profesorRepo.findByMail(email);
        Optional<Estudiante> estudiante = estudianteRepo.findByMail(email);

        if (admin.isPresent() || profesor.isPresent() || estudiante.isPresent()) {
            String token = JwtTokenGenerated.generar(email);
            mailService.enviarCorreoConToken(email, token);
            return "Correo de recuperación enviado.";
        } else {
            return "Correo no encontrado.";
        }
    }

    public String resetearClave(String token, String nuevaClave) {
        try {
            String correo = JwtTokenGenerated.validar(token);

            Optional<Administrador> admin = adminRepo.findByMail(correo);
            if (admin.isPresent()) {
                Administrador a = admin.get();
                a.setClave(passwordEncoder.encode(nuevaClave));
                adminRepo.save(a);
                return "Contraseña actualizada para administrador.";
            }

            Optional<Profesor> profesor = profesorRepo.findByMail(correo);
            if (profesor.isPresent()) {
                Profesor p = profesor.get();
                p.setClave(passwordEncoder.encode(nuevaClave));
                profesorRepo.save(p);
                return "Contraseña actualizada para profesor.";
            }

            Optional<Estudiante> estudiante = estudianteRepo.findByMail(correo);
            if (estudiante.isPresent()) {
                Estudiante e = estudiante.get();
                e.setClave(passwordEncoder.encode(nuevaClave));
                estudianteRepo.save(e);
                return "Contraseña actualizada para estudiante.";
            }

            return "Usuario no encontrado.";
        } catch (Exception e) {
            return "Token inválido o expirado.";
        }
    }
}


