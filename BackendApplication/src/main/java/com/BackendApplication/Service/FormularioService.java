package com.BackendApplication.Service;

import com.BackendApplication.Model.FormularioDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class FormularioService {

    @Value("${email.remitente}")
    String remitente;

    @Value("${email.remitente.password}")
    String passwordRemitente;

    @Value("${email.destinatario}")
    String destinatario;

    @Value("${email.port}")
    String port;

    @Value("${email.host}")
    String host;

        public boolean enviar(FormularioDto formulario) throws MessagingException {

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.connectiontimeout", "10000");
            props.put("mail.smtp.timeout", "10000");



            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(remitente,passwordRemitente);
                }
            });

            Message message = new MimeMessage(session);

            message.setSubject(remitente);

            message.setContent("prueba de mandar mail","text/html");

            try {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

            try {
                Transport.send(message);
                System.out.println("Correo enviado correctamente a: " + String.join(", ", destinatario));
                return true;
            } catch (MessagingException mex) {
                System.out.println("ENVIO DE CORREO DE CAMBIO DE INICIO: ERROR: " + mex.getMessage());
                return false;
            }

        }
    }
