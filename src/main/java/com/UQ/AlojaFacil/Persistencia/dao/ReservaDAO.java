package com.UQ.AlojaFacil.Persistencia.dao;

import com.UQ.AlojaFacil.Negocio.dto.ActualizarReservaDTO;
import com.UQ.AlojaFacil.Negocio.dto.CrearReservaDTO;
import com.UQ.AlojaFacil.Negocio.dto.ReservaDTO;
import com.UQ.AlojaFacil.Persistencia.Repositorio.ReservaRepository;
import com.UQ.AlojaFacil.Persistencia.entity.ReservaEntity;
import com.UQ.AlojaFacil.Persistencia.mapper.ReservaMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
@Slf4j
public class ReservaDAO {

    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;

    public ReservaDTO save(CrearReservaDTO crearReservaDTO){
        ReservaEntity entity=reservaMapper.toEntity(crearReservaDTO);
        ReservaEntity guardarReserva=reservaRepository.save(entity);
        return reservaMapper.toDTO(guardarReserva);
    }

    public boolean verificarInmueble(Long idInmueble){
        return reservaRepository.existsByInmuebleEntity_Id(idInmueble);
    }

    public boolean verificarHuesped(Long idHuesped){
        return reservaRepository.existsByHuespedEntity_Id(idHuesped);
    }

    public Optional<ReservaDTO> findById(Long id){
        return reservaRepository.findById(id).map(reservaMapper::toDTO);
    }

    public Optional<ReservaDTO>actualizarReserva(Long id, ActualizarReservaDTO actualizarReservaDTO){
        return reservaRepository.findById(id).
                map(existingEntity->{
                    reservaMapper.actualizarEntityFronDTO(actualizarReservaDTO,existingEntity);
                    ReservaEntity actualizar=reservaRepository.save(existingEntity);
                    return reservaMapper.toDTO(actualizar);
                });
    }

}
