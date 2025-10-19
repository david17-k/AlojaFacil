package com.UQ.AlojaFacil.Negocio.Servicio;


import com.UQ.AlojaFacil.Negocio.dto.ComentarioDTO;
import com.UQ.AlojaFacil.Negocio.dto.PublicarComentarioDTO;

public interface ComentarioServicio {

    ComentarioDTO crearComentario(PublicarComentarioDTO publicarComentarioDTO);
}
