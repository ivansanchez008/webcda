package com.BackendApplication.Controller;

import com.BackendApplication.Model.FormularioDto;
import com.BackendApplication.Service.CaptchaService;
import com.BackendApplication.Service.FormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class FormularioController {

    @Autowired
    private FormularioService formularioService;

    @Autowired
    private CaptchaService captchaService;

    @Value("${captcha.key}")
    private String key;

    @PostMapping("/enviarFormulario")
    public ResponseEntity enviarFormulario (@ModelAttribute FormularioDto formulario) throws MessagingException {
        if(captchaService.validateRecaptcha(formulario.getCaptcha())){
            return new ResponseEntity((formularioService.enviar(formulario)) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/obtenerCaptchaKey")
    public ResponseEntity<String> obtenerkey (){
        return new ResponseEntity<>(key,HttpStatus.OK);
    }

}

