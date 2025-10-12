package com.UQ.AlojaFacil.Persistencia.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Inmueble")
public class InmuebleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //***Anfitrion del alojamiento***
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anfitrion_id")
    private AnfitrionEntity anfitrionEntity;

    private String titulo;

    private String descripcion;

    @Column(name = "precio_noche")
    private double precioNoche;

    private String servicios;

    @Column(name = "num_personas")
    private int numPersonas;

    //***Coordenadas del alojamineto***

    private String ciudad;

    private String direccion;

    private double latitud;

    private double longitud;




}
