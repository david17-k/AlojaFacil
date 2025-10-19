package com.UQ.AlojaFacil.Negocio.Servicio.impl;


import com.UQ.AlojaFacil.Negocio.Servicio.ComentarioServicio;
import com.UQ.AlojaFacil.Negocio.dto.ComentarioDTO;
import com.UQ.AlojaFacil.Negocio.dto.PublicarComentarioDTO;
import com.UQ.AlojaFacil.Persistencia.Repositorio.HuespedRepository;
import com.UQ.AlojaFacil.Persistencia.Repositorio.InmuebleRepository;
import com.UQ.AlojaFacil.Persistencia.dao.ComentarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentarioDAO comentarioDAO;
    private final HuespedRepository huespedRepository;
    private final InmuebleRepository inmuebleRepository;

    @Override
    public ComentarioDTO crearComentario(PublicarComentarioDTO publicarComentarioDTO) {
        log.info("Creando nuevo comentario{}",publicarComentarioDTO.getIdInmueble());
        huespedRepository.findById(publicarComentarioDTO.getIdHuesped());
        inmuebleRepository.findById(publicarComentarioDTO.getIdInmueble());
        validarComentario(publicarComentarioDTO);
        ComentarioDTO publicar=comentarioDAO.save(publicarComentarioDTO);
        log.info("El comentario se publico{}",publicar.getId());
        return publicar;
    }

    private void validarComentario(PublicarComentarioDTO publicarComentarioDTO){
        if(publicarComentarioDTO.getCalificacion()==0){
            throw new IllegalArgumentException("La calificacion es requeridad para publicar");

        }
        if(publicarComentarioDTO.getComentario().trim().length()>150){
            throw new IllegalArgumentException("El comentario sobre el numero de caracteres");
        }
    }
}
