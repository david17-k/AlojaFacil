package com.UQ.AlojaFacil.Negocio.Servicio.impl;

import com.UQ.AlojaFacil.Negocio.Servicio.AnfitrionServicio;
import com.UQ.AlojaFacil.Negocio.dto.ActualizarAnfitrionDTO;
import com.UQ.AlojaFacil.Negocio.dto.AnfitrionDTO;
import com.UQ.AlojaFacil.Negocio.dto.CrearAnfitrionDTO;
import com.UQ.AlojaFacil.Persistencia.dao.AnfitrionDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AnfitrionServicioImpl implements AnfitrionServicio {

    private final AnfitrionDAO anfitrionDAO;


    @Override
    public AnfitrionDTO crearAnfitrion(CrearAnfitrionDTO crearAnfitrionDTO) {
        log.info("Creando nuevo anfitrion {}",crearAnfitrionDTO.getEmail());
        if(anfitrionDAO.existsByEmail(crearAnfitrionDTO.getEmail())){
            log.warn("Intento de crear anfitrion con email duplicado");
            throw new IllegalArgumentException("El correo ya se encuentra registrado");
        }
        validarDatosAnfitrion(crearAnfitrionDTO);
        AnfitrionDTO creaAnfitrion=anfitrionDAO.save(crearAnfitrionDTO);
        log.info("Anfitrion creado con exito con ID{}",creaAnfitrion.getId());
        return creaAnfitrion;
    }

    @Override
    @Transactional(readOnly = true)
    public AnfitrionDTO getAnfitrionById(Long id) {
        log.info("Buscando anfitrion por ID{}",id);
        return anfitrionDAO.findById(id).orElseThrow(()->{
                log.warn("Anfitrion no encontrado el ID{}",id);
                return new RuntimeException("Anfitrion no encontrado con ID{}"+id);
        });

    }

    @Override
    public List<AnfitrionDTO> getAllAnfitrion() {
        return List.of();
    }

    @Override
    public AnfitrionDTO actualizarAnfitrion(Long id, ActualizarAnfitrionDTO anfitrionDTO) {
        log.info("Actualizar anfitrion con ID{}",id);
        if(!anfitrionDAO.findById(id).isPresent()){
            log.warn("Se intento actualizar un anfitrion no registrado con ID{}",id);
            throw new RuntimeException("Anfitrion no encontrado");
        }
        validarActualizarAnfitrion(anfitrionDTO);
        AnfitrionDTO actualizaAnfitrion=anfitrionDAO.actualizarAnfitrion(id, anfitrionDTO)
                .orElseThrow(()->new RuntimeException("Error al actualizar anfitrion"));
        log.info("Anfitrion actualizado con ID{}",id);
        return actualizaAnfitrion;
    }

    @Override
    public void eliminarAnfitrion(Long id) {
        log.info("Eliminando anfitrion con ID{}",id);

        AnfitrionDTO anfitrion=getAnfitrionById(id);

        Long inmueble= anfitrionDAO.countInmuebleByAnfitrionId(id);
        if(inmueble>0){
        log.warn("Intento eliminar un Anfitrion con inmubles a su nombre ID{}",id);
        throw new IllegalStateException("No se puede borrar anfitriones con inmuebles a su nombre");
        }
        boolean borrar=anfitrionDAO.deletById(id);
        if(!borrar){
            throw new RuntimeException("Error de eliminacion de anfitrion");
        }
        log.info("Anfitrion eliminado correctamente");

    }


    private void validarDatosAnfitrion(CrearAnfitrionDTO crearAnfitrionDTO){
        if(crearAnfitrionDTO.getNombre()==null || crearAnfitrionDTO.getNombre().trim().isEmpty()){
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if(crearAnfitrionDTO.getEmail()==null || crearAnfitrionDTO.getEmail().trim().isEmpty()){
            throw new IllegalArgumentException("El correo es obligatorio");
        }

        if(crearAnfitrionDTO.getCelular()==null || crearAnfitrionDTO.getCelular().isEmpty()){
            throw new IllegalArgumentException("El numero celular es obligatorio");
        }
        if(crearAnfitrionDTO.getFechaNacimiento()==null){
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria");
        }
    }

    private void validarActualizarAnfitrion(ActualizarAnfitrionDTO actualizar){
        if(actualizar.getNombre()==null || actualizar.getNombre().trim().isEmpty()){
            throw new IllegalArgumentException("El nombre es obligatori");
        }
        if(actualizar.getCelular()==null || actualizar.getCelular().trim().isEmpty()){
            throw new IllegalArgumentException("El celular es obligatorio");
        }
    }
}
