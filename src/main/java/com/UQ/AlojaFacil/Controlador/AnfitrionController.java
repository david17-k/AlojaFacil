package com.UQ.AlojaFacil.Controlador;


import com.UQ.AlojaFacil.Negocio.Servicio.AnfitrionServicio;
import com.UQ.AlojaFacil.Negocio.dto.ActualizarAnfitrionDTO;
import com.UQ.AlojaFacil.Negocio.dto.AnfitrionDTO;
import com.UQ.AlojaFacil.Negocio.dto.CrearAnfitrionDTO;
import com.UQ.AlojaFacil.Negocio.dto.HuespedDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v0/anfitrion")
public class AnfitrionController {

    private final AnfitrionServicio anfitrionServicio;

    @PostMapping
    @Operation(summary = "Crear anfitrion",description = "Guardar anfitrion")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Anfitrion creado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HuespedDTO.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos invalido o email duplicado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Anfitrion no creado", content = @Content)
    })
    public ResponseEntity<AnfitrionDTO>crearAnfitrion(@Parameter(description = "Crear nuevo anfitrion",required = true)
            @RequestBody CrearAnfitrionDTO crearAnfitrionDTO){
        log.info("DTO recibido desde postman{}",crearAnfitrionDTO);
        log.info("Clase DTO que está llegando: {}", crearAnfitrionDTO.getClass().getName());

        log.debug("POST api/v0/anfitrion -Crear anfitrion :{}",crearAnfitrionDTO.getEmail());
        try {
            AnfitrionDTO creaAnfitrion = anfitrionServicio.crearAnfitrion(crearAnfitrionDTO);
            log.info("Anfitrion creado con exito con ID:{}", creaAnfitrion.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(creaAnfitrion);
        }catch (IllegalArgumentException i){
            log.warn("Error de validacion de datos anfitrion no creado {}", i.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anfitrion encontrado con exito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HuespedDTO.class))),

            @ApiResponse(responseCode = "400", description = "Formato incorrecto",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),

            @ApiResponse(responseCode = "404", description = "Anfitrion no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),



            @ApiResponse(responseCode = "500", description = "Error del servido",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
    })
    public ResponseEntity<AnfitrionDTO>buscarAnfitrionId(@Parameter(description = "Buscar el anfitrion por id",required = true)
                                                         @PathVariable Long id){
        log.debug("GET /api/v0/anfitrion - Buscando anfitrion con ID{} ",id);

        try{
            AnfitrionDTO anfitrion=anfitrionServicio.getAnfitrionById(id);
            return ResponseEntity.ok(anfitrion);
        }catch (RuntimeException e){
            if(e.getMessage().contains("formato")){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            log.warn("Anfitrion no registrado con ID{}",id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AnfitrionDTO>>obtenerAnfitriones(){
        log.debug("GET -Obtener todos los huespedes");
        List<AnfitrionDTO>anfitrion=anfitrionServicio.getAllAnfitrion();
        return ResponseEntity.ok(anfitrion);
    }


    //***Actualizar Anfitrion***
    @PutMapping("/{id}")
    @Operation(summary = "Actualiar datos de anfitrion", description = "Actualia los datos del anfitrion existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Datos del anfitrion actualizados correctamente",
                    content = @Content(mediaType = "appliaction/json",
                            schema = @Schema(implementation = HuespedDTO.class))),

            @ApiResponse(responseCode = "400", description = "Datos no actualizados del anfitrion",
                    content = @Content(mediaType = "applicatio/json",
                            schema = @Schema(implementation = String.class))),

            @ApiResponse(responseCode = "404",description = "Anfitrion no encontrado"),

            @ApiResponse(responseCode = "500", description = "Error del servidor",
                    content = @Content(mediaType = "appliaction/json",
                            schema = @Schema(implementation = HuespedDTO.class)))
    })
    public ResponseEntity<AnfitrionDTO>actualizarAnfitrion(@Parameter(description = "Actualiar Anfitrion",required = true)
                                                           @PathVariable Long id,
                                                           @Parameter(description = "Datos actualizados del Anfitrion",required = true)
                                                           @RequestBody ActualizarAnfitrionDTO actualizar){
        log.debug("PUT /api/v0/anfitrion -Actualizar Anfitrion{}",id);

        try {
            AnfitrionDTO actualizaAnfitrion=anfitrionServicio.actualizarAnfitrion(id,actualizar);
            log.info("Anfitrion actualizado con exito ID{}",id);
            return ResponseEntity.ok(actualizaAnfitrion);
        }catch (RuntimeException e){
            if(e.getMessage().contains("no encontrado")){
                log.warn("Anifitrion no encontrado para actualizar");
                return ResponseEntity.notFound().build();
            }
            log.warn("Error al actualizar anfitrion con ID{}:{}",id,e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }


    //***Eliminar Anfitrion**
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar anfitrion",
            description ="Eliminar anfitrion del sistema,no debe tener inmuble a su nombre" )
    @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Anfitrion eliminado",
            content = @Content(mediaType = "appliaction/json",
                    schema = @Schema(implementation = HuespedDTO.class))),

    @ApiResponse(responseCode = "404", description = "Anfitrion no encontrado",
            content = @Content(mediaType = "applicatio/json",
                    schema = @Schema(implementation = String.class))),

    @ApiResponse(responseCode = "409",
            description = "No se puede eliminar anfitrion tiene alojamientos a su nombre"),

    @ApiResponse(responseCode = "500", description = "Error del servidor",
            content = @Content(mediaType = "appliaction/json",
                    schema = @Schema(implementation = HuespedDTO.class)))})

    public ResponseEntity<Void>eliminarAnfitrion(@Parameter(description = "ID del anfitrion a eliminar",required = true)
                                                 @PathVariable Long id){
        log.info("DELETE /api/v0/anfitrion/ - Eliminar anfitrion con ID{}",id);

        try {
            anfitrionServicio.eliminarAnfitrion(id);
            log.info("Eliminacion correcta del anfitrion");
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            if(e.getMessage().contains("no encontrado")){
                log.warn("Anfitrion no encontrado");
                return ResponseEntity.notFound().build();
            } else if (e.getMessage().contains("inmuebles")) {
                log.warn("Intento eliminar anfitrion con Inmubles ID{}",id);
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            log.warn("Error al intentar eliminar anfitrion");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }




    }





}
