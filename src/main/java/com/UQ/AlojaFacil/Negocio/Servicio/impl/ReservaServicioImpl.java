package com.UQ.AlojaFacil.Negocio.Servicio.impl;

import com.UQ.AlojaFacil.Negocio.Estado.EstadoReserva;
import com.UQ.AlojaFacil.Negocio.Servicio.HuespedServicio;
import com.UQ.AlojaFacil.Negocio.Servicio.InmuebleServicio;
import com.UQ.AlojaFacil.Negocio.Servicio.ReservaServicio;
import com.UQ.AlojaFacil.Negocio.dto.ActualizarReservaDTO;
import com.UQ.AlojaFacil.Negocio.dto.CrearReservaDTO;
import com.UQ.AlojaFacil.Negocio.dto.InmuebleDTO;
import com.UQ.AlojaFacil.Negocio.dto.ReservaDTO;
import com.UQ.AlojaFacil.Persistencia.dao.ReservaDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ReservaServicioImpl implements ReservaServicio {

    private final ReservaDAO reservaDAO;
    private final HuespedServicio huespedServicio;
    private final InmuebleServicio inmuebleServicio;


    @Override
    public ReservaDTO crearReserva(CrearReservaDTO crearReservaDTO) {
        log.info("Creando nueva reserva con ID{}",crearReservaDTO.getIdHuesped());
        if(reservaDAO.verificarInmueble(crearReservaDTO.getIdInmueble())) {
           log.warn("El inmueble ya esta reservado ID{} ",crearReservaDTO.getIdInmueble());
           throw new IllegalArgumentException("Ya esta reservado el inmueble");
        }
        validacionReserva(crearReservaDTO);
        huespedServicio.getHuespedById(crearReservaDTO.getIdHuesped());
        inmuebleServicio.getInmuebleId(crearReservaDTO.getIdInmueble());
        ReservaDTO creaNuevaReserva = reservaDAO.save(crearReservaDTO);
        log.info("Se creo la reserva Correctamente con ID{}", crearReservaDTO.getFechaReserva());
        return creaNuevaReserva;
    }

    //***Actualizar Reserva***
    @Override
    public ReservaDTO actualizarReserva(Long id,ActualizarReservaDTO actualizarReservaDTO) {
        log.info("Actualizar Reserva con ID{}",actualizarReservaDTO.getEstado());
        if(!reservaDAO.findById(id).isPresent()){
            log.warn("Se intento actualizar un reserva que no existe{}",id);
            throw new RuntimeException("No se encontro la reserva");
        }
        actualizarEstado(actualizarReservaDTO);
        ReservaDTO actualizar=reservaDAO.actualizarReserva(id,actualizarReservaDTO).
                orElseThrow(()->new RuntimeException("Error al actualizar Reserva"));
        log.info("La reserva se actualizo correctamente{}",id);
        return actualizar;
    }

    //***Validacion de Reserva***
    private void validacionReserva(CrearReservaDTO crearReservaDTO){
        InmuebleDTO reserva=inmuebleServicio.getInmuebleId(crearReservaDTO.getIdInmueble());
        if(crearReservaDTO.getFechaReserva().isBefore(LocalDate.now())){
           log.warn("Fecha pasada {}",crearReservaDTO.getFechaReserva());
           throw new IllegalArgumentException("La fecha ingresada es pasada");
        }
        if(crearReservaDTO.getNumHuespedes()>reserva.getNumPersonas()){
            log.warn("El numero de personas excede la capacidad del inmueble{}",crearReservaDTO.getIdInmueble());
            throw new IllegalArgumentException("El numero de personas excede la capacidad del inmueble");
        }
        if(reservaDAO.verificarHuesped(crearReservaDTO.getIdHuesped())){
            log.warn("El huesped ya cuenta con una reserva a su nombre{}",crearReservaDTO.getIdHuesped());
            throw new IllegalArgumentException("El huesped ya tiene una reserva pendiente");
        }
    }

    //***Validar estado reserva***
    private EstadoReserva actualizarEstado(ActualizarReservaDTO actualizarReservaDTO){
        return switch (actualizarReservaDTO.getEstado()) {
            case APROBADA -> {
                log.info("LA RESERVA SE APROVO");
                yield EstadoReserva.APROBADA;
            }
            case RECHAZADA -> {
                log.info("SE RECHAZO LA RESERVA");
                yield EstadoReserva.RECHAZADA;
            }
            default -> null;
        };
    }





}
