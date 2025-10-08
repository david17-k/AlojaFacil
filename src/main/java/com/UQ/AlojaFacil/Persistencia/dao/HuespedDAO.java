package com.UQ.AlojaFacil.Persistencia.dao;

import com.UQ.AlojaFacil.Negocio.dto.ActulizarHuespedDTO;
import com.UQ.AlojaFacil.Negocio.dto.CrearHuespetDTO;
import com.UQ.AlojaFacil.Negocio.dto.HuespedDTO;
import com.UQ.AlojaFacil.Persistencia.Repositorio.HuespedRepository;
import com.UQ.AlojaFacil.Persistencia.entity.HuespedEntity;
import com.UQ.AlojaFacil.Persistencia.mapper.HuespedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HuespedDAO {

    private final HuespedRepository huespedRepository;
    private final HuespedMapper huespedMapper;


    public HuespedDTO save(CrearHuespetDTO crearHuespetDTO){
        HuespedEntity entity=huespedMapper.toEntity(crearHuespetDTO);
        HuespedEntity guardarEntity=huespedRepository.save(entity);
        return huespedMapper.toDTO(guardarEntity);
    }

    public Optional<HuespedDTO>findByEmail(String email){
        return huespedRepository.findByEmail(email).map(huespedMapper::toDTO);
    }

    public boolean existsByEmail(String email){
        return huespedRepository.existsByEmail(email);
    }

    public Optional<HuespedDTO>findById(Long id){
        return huespedRepository.findById(id).map(huespedMapper::toDTO);
    }

    public List<HuespedDTO>getAllHuespedes(){
        List<HuespedEntity>huespedEntities=huespedRepository.findAll();
        return huespedMapper.toDTOList(huespedEntities);
    }

    public Optional<HuespedDTO>actualizar(Long id, ActulizarHuespedDTO actulizarHuespedDTO){
        return huespedRepository.findById(id).
                map(existingEntity->{
                    huespedMapper.actualizarEntiyFromDTO(actulizarHuespedDTO,existingEntity);
                    HuespedEntity actualizarEntity=huespedRepository.save(existingEntity);
                    return huespedMapper.toDTO(actualizarEntity);
                });
    }



}
