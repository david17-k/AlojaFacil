package com.UQ.AlojaFacil.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;

@Entity
public class Huesped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    @Setter
    @Getter
    private String email;
    private String telefono;
    @Setter
    @Getter
    private String contraseña;
    @Getter
    private LocalDate fechaNacimiento;

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Huesped(String contraseña, String email, LocalDate fechaNacimiento, String nombre, String telefono) {
        this.contraseña = contraseña;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
        this.telefono = telefono;
    }
}
