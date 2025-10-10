package com.UQ.AlojaFacil.Negocio.Servicio;

import com.UQ.AlojaFacil.Negocio.dto.ActulizarHuespedDTO;
import com.UQ.AlojaFacil.Negocio.dto.CrearHuespetDTO;
import com.UQ.AlojaFacil.Negocio.dto.HuespedDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HuespedServicio {

    HuespedDTO crearHuesped(CrearHuespetDTO crearHuespetDTO) ;

    HuespedDTO getHuespedById(Long id);

    List<HuespedDTO>getAllHuespedes();

    HuespedDTO actualizarHuesped(Long id, ActulizarHuespedDTO actulizarHuespedDTO);

    void eliminarHuesped(Long id);


}
