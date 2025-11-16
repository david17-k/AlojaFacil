package com.UQ.AlojaFacil.Persistencia.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.lang.reflect.Type;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Comentario")
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(name = "fecha_comentario")
    private LocalDate fechaComentario;






}
