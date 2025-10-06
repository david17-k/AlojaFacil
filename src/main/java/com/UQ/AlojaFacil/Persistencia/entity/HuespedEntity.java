package com.UQ.AlojaFacil.Persistencia.entity;


import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Huesped")
@Data
@AllArgsConstructor


public class HuespedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String email;

    private String celular;

    private String contraseña;


    private LocalDate fechaNacimiento;


    @Column(name ="fecha_creacion_registro")
     private LocalDateTime fechaCreacionRegistro;




}
