package com.rest.demo.GenerarToken;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtTokenGenerated {

    private static final String SECRET_KEY = "mi_clave_secreta_super_segura";

    public static String generar(String correo) {
        long expirationTime = 1000 * 60 * 15; // 15 minutos
        return Jwts.builder()
                .setSubject(correo)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static String validar(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
