package com.UQ.AlojaFacil.Controlador;

import com.UQ.AlojaFacil.Negocio.Servicio.ReservaServicio;
import com.UQ.AlojaFacil.Negocio.dto.CrearInmuebleDTO;
import com.UQ.AlojaFacil.Negocio.dto.CrearReservaDTO;
import com.UQ.AlojaFacil.Negocio.dto.ReservaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v0/reserva")
public class ReservaController {

    private final ReservaServicio reservaServicio;

    @PostMapping
    @Operation(summary = "Reservar a inmueble",description = "Reservar inmueble")
    public ResponseEntity<ReservaDTO>crearReserva(@Parameter(description = "Reservar inmueble por ID",
                                                            required = true)
                                                      @RequestBody CrearReservaDTO crearReservaDTO){
        log.info("POST /api/v0/reserva -Crear nueva reserva{}",crearReservaDTO.getIdHuesped());
        try {
            ReservaDTO creaReserva=reservaServicio.crearReserva(crearReservaDTO);
            log.info("Creando Nueva reserva con ID{}",creaReserva.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(creaReserva);
        }catch (RuntimeException e){
          return  ResponseEntity.badRequest().build();
        }
    }
}
