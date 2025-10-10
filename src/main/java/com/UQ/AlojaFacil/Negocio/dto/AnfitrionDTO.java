package com.UQ.AlojaFacil.Negocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AnfitrionDTO {

    @Schema(description = "Id unico del anfitrion",example = "1")
    Long id;

    @Schema(description = "Nombre completo del anfitrion",example = "David Castillo",required = true,maxLength = 30)
    private String nombre;

    @Schema(description = "Email unico del anfitrion",example = "davidcastillo67@gmail.com",accessMode = Schema.AccessMode.READ_ONLY)
    private String email;

    @Schema(description = "Numero telefonico del anfitrion",example = "3117684965",maxLength = 10)
    private String celular;

    @Schema(description = "Contraseña segura ",example = "davidCas67#",minLength = 8)
    private String contraseña;

    @Schema(description = "Fecha de nacimiento del anfitrion",example = "09-03-1999",required = true)
    private LocalDate fechaNacimiento;



    @Schema(description = "Fecha de creacion de registro anfitrion",example = "2025-09-07T15:45:00",accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;;

}
