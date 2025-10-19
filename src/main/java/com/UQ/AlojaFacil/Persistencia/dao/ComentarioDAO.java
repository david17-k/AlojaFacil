package com.UQ.AlojaFacil.Persistencia.dao;

import com.UQ.AlojaFacil.Negocio.dto.ComentarioDTO;
import com.UQ.AlojaFacil.Negocio.dto.PublicarComentarioDTO;
import com.UQ.AlojaFacil.Persistencia.Repositorio.ComentarioRepository;
import com.UQ.AlojaFacil.Persistencia.entity.ComentarioEntity;
import com.UQ.AlojaFacil.Persistencia.mapper.ComentarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ComentarioDAO {

    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;


    public ComentarioDTO save(PublicarComentarioDTO publicarComentarioDTO){
        ComentarioEntity comentario=comentarioMapper.toEntity(publicarComentarioDTO);
        ComentarioEntity publicar=comentarioRepository.save(comentario);
        return comentarioMapper.toDTO(publicar);
    }
}
