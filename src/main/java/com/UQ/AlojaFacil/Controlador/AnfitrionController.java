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
            @ApiResponse(responseCode = "404", description = "Anfitrion no creado", content = @Content)
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
    public ResponseEntity<AnfitrionDTO>buscarAnfitrionId(@Parameter(description = "Buscar el anfitrion por id",required = true)
                                                         @PathVariable Long id){
        log.debug("GET /api/v0/anfitrion - Buscando anfitrion con ID{} ",id);

        try{
            AnfitrionDTO anfitrion=anfitrionServicio.getAnfitrionById(id);
            return ResponseEntity.ok(anfitrion);
        }catch (RuntimeException e){
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
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar anfitrion",
            description = "Elimina un anfitrion del sistema. No se puede eliminar si tiene inmubles asociados."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "anfitrion eliminado exitosamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "anfitrion no encontrado"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "No se puede eliminar: anfitrion tiene inmuebles asociados"
            )
    })
    public ResponseEntity<Void> borrarAnfitrion(
            @Parameter(description = "ID del anfitrion a eliminar", required = true, example = "1")
            @PathVariable Long id
    ) {
        log.info("DELETE /api/v0/anfitrion/ - Eliminando anfitrion{}", id);

        try {
           anfitrionServicio.eliminarAnfitrion(id);
            log.info("anfitrion eliminado exitosamente ID: {}", id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            if (e.getMessage().contains("no encontrado")) {
                log.warn("anfitrion no encontrado para eliminar ID: {}", id);
                return ResponseEntity.notFound().build();
            } else if (e.getMessage().contains("producto")) {
                log.warn("Intento de eliminar anfitrion con inmuebles a su nombre ID: {}", id);
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            log.error("Error al eliminar anfitrion ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
