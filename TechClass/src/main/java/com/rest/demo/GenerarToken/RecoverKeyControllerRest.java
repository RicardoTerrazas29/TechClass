package com.rest.demo.GenerarToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class RecoverKeyControllerRest {

    @Autowired
    private RecoverKey recuperarClaveService;

    @PostMapping("/recuperar")
    public ResponseEntity<?> recuperar(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String resultado = recuperarClaveService.recuperarPorCorreo(email);

        if (resultado.startsWith("Correo de recuperación enviado")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.status(400).body(resultado);
        }
    }

    @PostMapping("/resetear-clave")
    public ResponseEntity<String> resetearClave(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String nuevaClave = request.get("nuevaClave");

        String resultado = recuperarClaveService.resetearClave(token, nuevaClave);

        if (resultado.startsWith("Contraseña actualizada")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.status(400).body(resultado);
        }
    }
}

