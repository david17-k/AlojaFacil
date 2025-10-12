package com.UQ.AlojaFacil.Negocio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class InmuebleDTO {


    @Schema(description = "id del alojamiento ",example = "1")
    private Long id;

    @Schema(description = "titulo del alojamiento",example = "Casa rural",maxLength = 100)
    private String titulo;

    @Schema(description = "descripcion detallada del inmueble",example = "casa rural con dos habitaciones, un baño y cerca a la ciudad",maxLength = 150)
    private String descripcion;

    @Schema(description = "precio por noche",example = "$200000")
    private double precioNoche;

    @Schema(description = "servicios con los que cuenta el alojamineto",example = "wifi,camping")
    private String servicios;

    @Schema(description = "numero de personas maxima", example = "4")
    private int numPersonas;

    //***Coordenadas del alojamineto***

    @Schema(description = "Ciudad donde se encuentra el alojamineto",example = "Circasia")
    private String ciudad;

    @Schema(description ="Direccion del alojamiento",example = "Barrio el porvenir,conjunto 2 , bloque 8")
    private String direccion;

    @Schema(description = "Latitud del inmueble",example = " 04º 37' 12”")
    private double latitud;

    @Schema(description = "Longitud del mueble",example = "75º 38' 20")
    private double longitud;

    //***Datos del Anfitrion***
    @Schema(description = "Id unico del anfitrion",example = "3",required = true)
    private Long idAnfitrion;

    @Schema(description = "Nombre del anfitrion",example = "David Castillo",required = true,accessMode = Schema.AccessMode.READ_ONLY)
    private String nombreAnfitrion;

    @Schema(description = "Correo electronico del anfitrion",example = "davidcastillo67@gmail.com",required = true,accessMode = Schema.AccessMode.READ_ONLY)
    private String emailAnfitrion;
}
