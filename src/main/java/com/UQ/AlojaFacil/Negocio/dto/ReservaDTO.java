package com.UQ.AlojaFacil.Negocio.dto;

import com.UQ.AlojaFacil.Negocio.Estado.EstadoReserva;
import com.UQ.AlojaFacil.Persistencia.entity.HuespedEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReservaDTO {

    @Schema(description = "Id de la reserva",example = "1")
    Long id;

    @Schema(description = "Fecha de la reserva",example = "12-12-2025",required = true)
    private LocalDate fechaReserva;

    @Schema(description = "Numero de huespedes que se van a hospedar",example = "4",required = true)
    private int numHuespedes;

    @Schema(description = "Estado en que se encuentra la reserva",example = "ESPERA")
    private EstadoReserva estado;

    //***DATOS DEL INMUEBLE***
    @Schema(description = "ID del inmueble reservado",example = "3")
    private Long idInmueble;

    @Schema(description = "Numero permitido de huespedes",example = "4")
    private int numPermitido;

    @Schema(description = "Precio por noche",example = "$200000")
    private double precioNoche;

    //***DATOS DEL HUESPED***
    @Schema(description = "ID del huesped",example = "2")
    private Long idHuesped;

    @Schema(description = "Nombre del huesped",example = "David Jurado")
    private String nombreHuesped;

    @Schema(description = "Email del huesped",example = "davidjurado89@gmail.com")
    private String emailHuesped;
}
