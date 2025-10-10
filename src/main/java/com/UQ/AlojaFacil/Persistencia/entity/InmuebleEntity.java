package com.UQ.AlojaFacil.Persistencia.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InmuebleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private AnfitrionEntity anfitrionEntity;

    private String titulo;

    private String descripcion;

    private double precioNoche;

    private String servicios;

    private int numPersonas;

    //***Coordenadas del alojamineto***

    private String ciudad;

    private String direccion;

    private String latitud;

    private String longitud;




}
