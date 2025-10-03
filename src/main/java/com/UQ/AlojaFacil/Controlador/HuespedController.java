package com.UQ.AlojaFacil.Controlador;


import com.UQ.AlojaFacil.Negocio.Servicio.HuespedServicio;
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


@RestController
@RequestMapping("/api/v0/huesped")
@Slf4j
@RequiredArgsConstructor
public class HuespedController {

    private final HuespedServicio huespedServicio;


    @Operation(summary = "Crear huesped", description = "Guardar huesped")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Huesped creado", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "404", description = "Huesped no creado", content = @Content)
    })

    @PostMapping
    public ResponseEntity<HuespedDTO>crearHuesped(@Parameter(description = "Datos del husped al crear",required = true)
                                                @RequestBody CrearHuespetDTO crearHuespetDTO){
        log.info("POST /api/v0/huesped - Crear Huesped : {} ",crearHuespetDTO.getEmail());

        try {
            HuespedDTO creaHuesped= huespedServicio.crearHuesped(crearHuespetDTO);
            log.info("Huesped creado con exito con ID {}",creaHuesped.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(creaHuesped);
        }catch (IllegalArgumentException i){
            log.warn("Error de validacion crear Huesped: {}", i.getMessage());
            return ResponseEntity.badRequest().build();
        }

        }

}