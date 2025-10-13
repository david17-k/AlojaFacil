package com.UQ.AlojaFacil.Negocio.dto;

import com.UQ.AlojaFacil.Negocio.Estado.EstadoReserva;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Crear Reserva ")
public class CrearReservaDTO {

    @Schema(description = "Fecha de la reserva",
            example = "2025-12-12",
            required = true)
    private LocalDate fechaReserva;

    @Schema(description = "Numero de huespedes que se van a hospedar",
            example = "4",required = true)
    private int numHuespedes;

    @Schema(description = "Estado en que se encuentra la reserva",
            example = "ESPERA")
    private EstadoReserva estado;

    //***ID inmueble reservado***
    @Schema(description = "Reserva del inmueble a resrvar",
            example = "3")
    private Long idInmueble;

    //***ID hueseped***
    @Schema(description = "ID del huesped",
            example = "2")
    private Long idHuesped;

}
