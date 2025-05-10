package com.rest.demo.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class MailService {

    private final String remitente = "equipotechclass@gmail.com"; // Correo de aplicacion
    private final String clave = "sngs ikhb xgnn vbey"; // Clave de aplicación

    private final Session sesion;

    public MailService() {
        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");

        this.sesion = Session.getInstance(propiedades, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });
    }

    @Async
    public void enviarCorreoConToken(String destinatario, String token) {
        try {
            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensaje.setSubject("🔒 Recuperación de contraseña - TechClass");

            String cuerpoHtml = "<html>" +
                "<body style='font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;'>" +
                "<div style='max-width: 600px; margin: auto; background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1);'>" +
                "<h2 style='color: #2E86C1; text-align: center;'>Recuperación de contraseña</h2>" +
                "<p>Hola,</p>" +
                "<p>Recibimos una solicitud para restablecer tu contraseña. Usa el siguiente código para continuar:</p>" +
                "<div style='text-align: center; margin: 30px 0;'>" +
                "<span style='display: inline-block; background-color: #2E86C1; color: #ffffff; padding: 15px 30px; font-size: 24px; font-weight: bold; border-radius: 6px;'>" + token + "</span>" +
                "</div>" +
                "<p style='text-align: center;'>Este código es válido por <b>15 minutos</b>.</p>" +
                "<p>Si no realizaste esta solicitud, puedes ignorar este mensaje.</p>" +
                "<br>" +
                "<p>Saludos,</p>" +
                "<p><b>El equipo de TechClass</b></p>" +
                "</div>" +
                "</body>" +
                "</html>";

            mensaje.setContent(cuerpoHtml, "text/html; charset=utf-8");

            Transport.send(mensaje);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}

