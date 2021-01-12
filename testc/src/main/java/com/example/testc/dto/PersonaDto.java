package com.example.testc.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonaDto implements Serializable {
    private String nombre;
    private String correo;
    private String cargo;
    private Date fechaNacimiento;

}
