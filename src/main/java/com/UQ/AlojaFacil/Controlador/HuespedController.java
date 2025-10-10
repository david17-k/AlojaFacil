package com.UQ.AlojaFacil.Controlador;


import com.UQ.AlojaFacil.Negocio.Servicio.HuespedServicio;
import com.UQ.AlojaFacil.Negocio.dto.ActulizarHuespedDTO;
import com.UQ.AlojaFacil.Negocio.dto.CrearHuespetDTO;
import com.UQ.AlojaFacil.Negocio.dto.HuespedDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v0/huesped")
@Slf4j
@RequiredArgsConstructor
public class HuespedController {

    private final HuespedServicio huespedServicio;




    @PostMapping
    @Operation(summary = "Crear huesped", description = "Guardar huesped")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Huesped creado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HuespedDTO.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos invalido o email duplicado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Huesped no creado", content = @Content)
    })
    public ResponseEntity<HuespedDTO> crearHuesped(@Parameter(description = "Datos del husped al crear", required = true)
                                                   @RequestBody CrearHuespetDTO crearHuespetDTO) {
        log.info("POST /api/v0/huesped - Crear Huesped : {} ", crearHuespetDTO.getEmail());
        try {
            HuespedDTO creaHuesped = huespedServicio.crearHuesped(crearHuespetDTO);
            log.info("Huesped creado con exito con ID {}", creaHuesped.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(creaHuesped);
        } catch (IllegalArgumentException i) {
            log.warn("Error de validacion crear Huesped: {}", i.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }



    @GetMapping("/{id}")
    @Operation(summary = "Buscar huesped por ID{}", description = "Busqueda de huesped")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Huesped encontrado con exito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HuespedDTO.class))),

            @ApiResponse(responseCode = "404", description = "Huesped no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),

            @ApiResponse(responseCode = "500", description = "Error del servido",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
    })
    public ResponseEntity<HuespedDTO> buscarHuespedId(@Parameter(description = "Id para buscar huesped", required = true, example = "1")
                                                      @PathVariable Long id) {
        log.debug("GET /api/v0/huesped/{} - Buscancando Huesped", id);

        try {
            HuespedDTO huesped = huespedServicio.getHuespedById(id);
            return ResponseEntity.ok(huesped);
        } catch (RuntimeException e) {
            log.warn("Huesped no encontrado");
            return ResponseEntity.notFound().build();
        }
    }

    //***BUSCAR HUESPED POR ID****
    @GetMapping
    public ResponseEntity<List<HuespedDTO>> obtenerHuespedes() {
        log.debug("Mostrar todos los huespedes");

        List<HuespedDTO> huespedes = huespedServicio.getAllHuespedes();
        return ResponseEntity.ok(huespedes);


    }


    //***ACTUALIZAR DATOS DE HUESPED***

    @PutMapping("/{id}")
    @Operation(summary = "Actualiar datos de huesped", description = "Actualia los datos del huesped existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Datos del huesped actualizados correctament",
                    content = @Content(mediaType = "appliaction/json",
                            schema = @Schema(implementation = HuespedDTO.class))),

            @ApiResponse(responseCode = "401", description = "Datos no actualizados del huesped",
                    content = @Content(mediaType = "applicatio/json",
                            schema = @Schema(implementation = String.class))),

            @ApiResponse(responseCode = "500", description = "Error del seridor",
                    content = @Content(mediaType = "appliaction/json",
                            schema = @Schema(implementation = HuespedDTO.class)))
    })
    public ResponseEntity<HuespedDTO> actualizarHuesped(
            @Parameter(description = "Actualizar datos del huesped", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Datos actualizados del huesped", required = true)
            @RequestBody ActulizarHuespedDTO actulizarHuespedDTO) {

        log.debug("PUT /api/v0/huesped/{}  Actualizar Datos", id);

        try {
            HuespedDTO actualizarHuesped = huespedServicio.actualizarHuesped(id, actulizarHuespedDTO);
            log.info("Huesped actualizado exitosamente ID:{}", id);
            return ResponseEntity.ok(actualizarHuesped);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("no encontrado")) {
                log.warn("Huesped no encontrado para actualizarlo");
                return ResponseEntity.notFound().build();
            }
            log.warn("Error al actualizar ID {}:{}", id, e.getMessage());
            return ResponseEntity.badRequest().build();
        }
        }

        /*
        //***ELIMINAR HUESPED***
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar huesped",
            description = "Elimina huesped del sistema.No se puede eliminar si tiene reservas asociadas")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Huesped eliminado con exito"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Huesped no encontrado"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "El huesped cuenta con una reserva a su nombre"
            )
    })
    public ResponseEntity<Void>eliminarHuesped(
            @Parameter(description = "ID del huesped a eliminar",required = true,example = "1")
            @PathVariable Long id){
        log.info("DELETE /api/v0/huesped/{} -Eliminar huesped",id);

        try{
            huespedServicio.
        }

    }
    )

         */
    }







