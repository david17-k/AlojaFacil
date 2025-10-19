package com.UQ.AlojaFacil.Negocio.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComentarioDTO {

    @Schema(description = "Id del comentario",example = "1")
    private Long id;

    @Schema(description = "Comentario del huesped",example = "Un baño huele muy mal")
    private String comentario;

    @Schema(description = "Calificacion",example = "3",maxLength = 5)
    private int calificacion;


    @Schema(description = "ID del inmueble reservado",example = "3")
    private Long idInmueble;

    //***DATOS HUESPED***
    @Schema(description = "ID del huesped comentario",example = "2")
    private Long idHuesped;

    @Schema(description = "Nombre del huesped",example = "Juan Pepe")
    private String nombreHuesped;


}
