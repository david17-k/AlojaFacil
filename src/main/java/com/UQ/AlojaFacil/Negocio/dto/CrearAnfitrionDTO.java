package com.UQ.AlojaFacil.Negocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para crear el anfitrion")
public class CrearAnfitrionDTO {

    @Schema(description = "Nombre completo del huesped",
            example ="David Castillo Urquijo",
            required = true,
            maxLength = 100)
    private String nombre;

    @Schema(description = "Correo unico del huesped",
            example = "davidcastillo89@gmail.com",
            required  = true,
            maxLength = 150)
    private String email;

    @Schema(description = "Numero de celular",
            example = "3117594667",
            required = true,
            maxLength = 10)
    private String celular;

    @Schema(description = "Contraseña segura",
            example = "Juan$kas89",
            required = true,
            minLength = 8)
    private String contraseña;

    @Schema(description = "Fecha de nacimiento",
            example = "17/05/1990",
            required = true)
    private LocalDate fechaNacimiento;
}
