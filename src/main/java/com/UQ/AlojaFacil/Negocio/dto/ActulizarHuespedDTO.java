package com.UQ.AlojaFacil.Negocio.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Actualizar Datos Huesped")
public class ActulizarHuespedDTO {

    @Schema(description = "Nombre completo del huesped",
            example ="Pepe Castillo Urquijo",
            required = true,
            maxLength = 100)
    private String nombre;


    @Schema(description = "Numero de celular",
            example = "3117594667",
            required = true,
            maxLength = 10)
    private String celular;



}
