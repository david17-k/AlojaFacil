package com.UQ.AlojaFacil.Negocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Publicar comentario")
public class PublicarComentarioDTO {

    @Schema(description = "Comentario",
            example = "El baño de la habitacion huele muy mal",
            required = true)
    private String comentario;

    @Schema(description = "Calificacion del alojamiento",
            example = "5"
            ,required = true)
    private int calificacion;

    @Schema(description = "ID inmuble",
            example = "1",
            required = true)
    private Long idInmueble;

    @Schema(description = "ID huesped",
            example = "2")
    private Long idHuesped;
}
