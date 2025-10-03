package com.UQ.AlojaFacil.Negocio.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HuespedDTO {

    @Schema(description = "Id del huesped",example = "1",accessMode = Schema.AccessMode.READ_ONLY )
    private Long id;

    @Schema(description = "Nombre completo",example = "Pepe Castillo Urquijo",required = true,maxLength = 100)
    private String nombre;

    @Schema(description = "Correo electronico unico", example = "castillopepe89@gmail.com",required = true,maxLength = 150)
    private String email;

    @Schema(description = "Contraseña huesped", example = "juanperez$12K",readOnly = true,maxLength = 100)
    private String contraseña;

    @Schema(description = "Numero celular del huesped" , example = "3124256591", required = true, maxLength = 10)
    private String celular;

    @Schema(description = "Fecha de nacimiento", example = "09/03/1999", required = true)
    private LocalDateTime fechaNacimiento;

    @Schema(description = "Fecha de creacion del registro", example = "2025-08-07T10:30:00",accessMode = Schema.AccessMode.READ_ONLY )
    private LocalDateTime fechaCreacionRegistro;
}
