package com.UQ.AlojaFacil.Persistencia.dao;


import com.UQ.AlojaFacil.Negocio.dto.*;
import com.UQ.AlojaFacil.Persistencia.Repositorio.AnfitrionRepository;
import com.UQ.AlojaFacil.Persistencia.entity.AnfitrionEntity;
import com.UQ.AlojaFacil.Persistencia.entity.HuespedEntity;
import com.UQ.AlojaFacil.Persistencia.mapper.AnfitrionMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AnfitrionDAO {

    private final AnfitrionMapper anfitrionMapper;
    private final AnfitrionRepository anfitrionRepository;

    public AnfitrionDTO save(CrearAnfitrionDTO crearAnfitrionDTO){
        AnfitrionEntity entity=anfitrionMapper.toEntity(crearAnfitrionDTO);
        AnfitrionEntity guardarEntity=anfitrionRepository.save(entity);
        return anfitrionMapper.toDTO(guardarEntity);
    }

    public boolean existsByEmail(String email){
        return anfitrionRepository.existsByEmail(email);
    }

    public Optional<AnfitrionDTO> findById(Long id){
        return anfitrionRepository.findById(id).map(anfitrionMapper::toDTO);
    }

    public Optional<AnfitrionDTO>actualizarAnfitrion(Long id, ActualizarAnfitrionDTO actualizarAnfitrionDTO){
        return anfitrionRepository.findById(id).
                map(existingEntity->{
                    anfitrionMapper.actualiarEntityFromDTO(actualizarAnfitrionDTO,existingEntity);
                    AnfitrionEntity actualizarEntity=anfitrionRepository.save(existingEntity);
                    return anfitrionMapper.toDTO(actualizarEntity);
                });
    }

}
