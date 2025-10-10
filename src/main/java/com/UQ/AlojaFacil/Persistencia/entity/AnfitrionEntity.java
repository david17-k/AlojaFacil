package com.UQ.AlojaFacil.Persistencia.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Anfitrion ")
@Table(name = "Anfitrion")
public class AnfitrionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String nombre;

    private String email;

    private String celular;

    private String contraseña;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @OneToMany(mappedBy = "anfitrionEntity", fetch = FetchType.LAZY )
    private List<InmuebleEntity>inmuebles;

    @CreatedDate
    @Column(name ="creacion_registro")
    private LocalDateTime createdAt;




}
