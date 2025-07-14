package com.BackendApplication.Model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormularioDto {
    private String nombre;
    private String email;
    private String telefono;
    private String mensaje;
    private String captcha;



}
