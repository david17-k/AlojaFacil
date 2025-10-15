package com.UQ.AlojaFacil.Negocio.Servicio;

import com.UQ.AlojaFacil.Negocio.dto.ActualizarReservaDTO;
import com.UQ.AlojaFacil.Negocio.dto.CrearReservaDTO;
import com.UQ.AlojaFacil.Negocio.dto.ReservaDTO;

public interface ReservaServicio {

    ReservaDTO crearReserva(CrearReservaDTO crearReservaDTO);

    ReservaDTO actualizarReserva(Long id,ActualizarReservaDTO actualizarReservaDTO);

}
