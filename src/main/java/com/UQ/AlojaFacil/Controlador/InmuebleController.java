package com.UQ.AlojaFacil.Controlador;

import com.UQ.AlojaFacil.Negocio.Servicio.InmuebleServicio;
import com.UQ.AlojaFacil.Negocio.dto.CrearInmuebleDTO;
import com.UQ.AlojaFacil.Negocio.dto.InmuebleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v0/inmueble")
public class InmuebleController {

    private final InmuebleServicio inmuebleServicio;

    @PostMapping
    @Operation(summary = "Crear alojamineto",description = "Guardar alojamiento")
    public ResponseEntity<InmuebleDTO>crearInmueble(@Parameter(description = "Datos para crear el alojamiento",required = true)
                                                    @RequestBody CrearInmuebleDTO crearInmuebleDTO){
        log.debug("POST /api/v0/inmueble -Crear Inmueble {}",crearInmuebleDTO.getIdAnfitrion());

        try {
            InmuebleDTO creaInmueble=inmuebleServicio.crearInmueble(crearInmuebleDTO);
            log.info("Alojamiento creado con exito{}",creaInmueble.getId());
            log.info("El anfitrion con ID{}",crearInmuebleDTO.getIdAnfitrion());
            return ResponseEntity.status(HttpStatus.CREATED).body(creaInmueble);

        }catch (IllegalArgumentException i){
            log.warn("Error de validacion en crear alojamiento{}",i.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


}
