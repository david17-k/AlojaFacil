package com.UQ.AlojaFacil.Persistencia.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Comentario")
public class ComentarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "calificacion")
    private int Calificacion;

    @JoinColumn(name = "huesped_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private HuespedEntity huespedEntity;

    @JoinColumn(name = "inmueble_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private InmuebleEntity inmuebleEntity;

    @Column(name = "fecha_comentario")
    private LocalDate fechaComentario;






}
