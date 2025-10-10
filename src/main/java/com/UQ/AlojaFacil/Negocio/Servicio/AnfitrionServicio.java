package com.UQ.AlojaFacil.Negocio.Servicio;

import com.UQ.AlojaFacil.Negocio.dto.ActualizarAnfitrionDTO;
import com.UQ.AlojaFacil.Negocio.dto.AnfitrionDTO;
import com.UQ.AlojaFacil.Negocio.dto.CrearAnfitrionDTO;

import java.util.List;

public interface AnfitrionServicio {

    AnfitrionDTO crearAnfitrion(CrearAnfitrionDTO crearAnfitrionDTO);

    AnfitrionDTO getAnfitrionById(Long id);

    List<AnfitrionDTO>getAllAnfitrion();

    AnfitrionDTO actualizarAnfitrion(Long id, ActualizarAnfitrionDTO anfitrionDTO);



}
