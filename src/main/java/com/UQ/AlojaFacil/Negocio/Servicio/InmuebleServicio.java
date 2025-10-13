package com.UQ.AlojaFacil.Negocio.Servicio;

import com.UQ.AlojaFacil.Negocio.dto.CrearInmuebleDTO;
import com.UQ.AlojaFacil.Negocio.dto.InmuebleDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface InmuebleServicio {

    InmuebleDTO crearInmueble(CrearInmuebleDTO crearInmuebleDTO);

    InmuebleDTO getInmuebleId(Long id);

    List<InmuebleDTO>getAllInmuebles();
}
