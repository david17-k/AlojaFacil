package com.UQ.AlojaFacil.Persistencia.dao;


import com.UQ.AlojaFacil.Negocio.dto.CrearInmuebleDTO;
import com.UQ.AlojaFacil.Negocio.dto.InmuebleDTO;
import com.UQ.AlojaFacil.Persistencia.Repositorio.InmuebleRepository;
import com.UQ.AlojaFacil.Persistencia.entity.InmuebleEntity;
import com.UQ.AlojaFacil.Persistencia.mapper.InmuebleMapper;
import com.UQ.AlojaFacil.config.OpenApiConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class InmuebleDAO {

    private final InmuebleRepository inmuebleRepository;
    private final InmuebleMapper inmuebleMapper;

    public InmuebleDTO save(CrearInmuebleDTO crearInmuebleDTO){
        InmuebleEntity entity=inmuebleMapper.toEntity(crearInmuebleDTO);
        InmuebleEntity guardarEntity=inmuebleRepository.save(entity);

        InmuebleEntity completo=inmuebleRepository.findByWithAnfitrion(guardarEntity.getId())
                .orElseThrow(()->new RuntimeException("Error al cargar datos"));
        return inmuebleMapper.toTDO(completo);
    }

    public Optional<InmuebleDTO>findByid(Long id){
        return inmuebleRepository.findById(id).map(inmuebleMapper::toTDO);
    }

    public Optional<InmuebleDTO>findByWithAnfitrion(Long id){
        return inmuebleRepository.findByWithAnfitrion(id).
                map(inmuebleMapper::toTDO);
    }








}
