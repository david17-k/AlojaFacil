package com.UQ.AlojaFacil.Persistencia.entity;

import com.UQ.AlojaFacil.Negocio.Estado.EstadoReserva;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Schema(description = "Reservas de huespedes")
@Table(name = "Reserva")
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "fecha_reserva")
    private LocalDate fechaReserva;

    @Column(name = "num_huespedes")
    private int numHuespedes;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_reserva")
    private EstadoReserva estado=EstadoReserva.ESPERA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inmueble")
    private InmuebleEntity inmuebleEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "huesped_id")
    private HuespedEntity huespedEntity;
}
