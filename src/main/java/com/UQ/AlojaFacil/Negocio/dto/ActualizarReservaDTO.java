package com.UQ.AlojaFacil.Negocio.dto;


import com.UQ.AlojaFacil.Negocio.Estado.EstadoReserva;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Cambiar estado de la reserva")
public class ActualizarReservaDTO {

    @Schema(description = "Actualiar estado de la reserava",
           example = "ACEPTADA",
            required = true)
    private EstadoReserva estado;
}
