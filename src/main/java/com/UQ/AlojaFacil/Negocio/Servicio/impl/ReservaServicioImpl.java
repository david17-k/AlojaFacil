package com.UQ.AlojaFacil.Negocio.Servicio.impl;

import com.UQ.AlojaFacil.Negocio.Estado.EstadoReserva;
import com.UQ.AlojaFacil.Negocio.Servicio.HuespedServicio;
import com.UQ.AlojaFacil.Negocio.Servicio.InmuebleServicio;
import com.UQ.AlojaFacil.Negocio.Servicio.ReservaServicio;
import com.UQ.AlojaFacil.Negocio.dto.CrearReservaDTO;
import com.UQ.AlojaFacil.Negocio.dto.ReservaDTO;
import com.UQ.AlojaFacil.Persistencia.dao.ReservaDAO;
import com.UQ.AlojaFacil.Persistencia.entity.HuespedEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        huespedServicio.getHuespedById(crearReservaDTO.getIdHuesped());
        inmuebleServicio.getInmuebleId(crearReservaDTO.getIdInmueble());
        validacionReserva(crearReservaDTO);
        ReservaDTO creaNuevaReserva=reservaDAO.save(crearReservaDTO);
        log.info("Se creo la reserva Correctamente con ID{}",crearReservaDTO.getFechaReserva());
        return creaNuevaReserva;
    }

    //***Validacion de Reserva***
    private void validacionReserva(CrearReservaDTO crearReservaDTO){
        if(crearReservaDTO.getFechaReserva().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("La fecha ingresada ya es pasada");
        }
    }



}
