package com.UQ.AlojaFacil.Negocio.Servicio.impl;

import com.UQ.AlojaFacil.Negocio.Servicio.HuespedServicio;
import com.UQ.AlojaFacil.Negocio.dto.ActulizarHuespedDTO;
import com.UQ.AlojaFacil.Negocio.dto.CrearHuespetDTO;
import com.UQ.AlojaFacil.Negocio.dto.HuespedDTO;
import com.UQ.AlojaFacil.Persistencia.dao.HuespedDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.beans.Transient;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class HuespedServicioImpl implements HuespedServicio {

    private final HuespedDAO huespedDAO;
    @Override
    public HuespedDTO crearHuesped(CrearHuespetDTO crearHuespetDTO) {
     log.info("Creando nuevo huesped con email:{}",crearHuespetDTO.getEmail());
     if(huespedDAO.existsByEmail(crearHuespetDTO.getEmail())){
         log.warn("Intento de crear huesped con email duplicado");
         throw new IllegalArgumentException("Ya existe el email");
     }
     validarCrearHuesped(crearHuespetDTO);

     HuespedDTO creaHuesped=huespedDAO.save(crearHuespetDTO);
     log.info("Huesped crado exitosamente con ID:{}", creaHuesped.getId());
     return creaHuesped;
    }

    @Override
    @Transactional(readOnly = true)
    public HuespedDTO getHuespedById(Long id) {
        log.info("Buscar id de huesped con ID{}",id);
            return huespedDAO.findById(id).orElseThrow(()->{
                    log.warn("No se encntro el ID{}",id);
                    return new  RuntimeException("Huesped no encontrado con ID{}"+id);
            });
    }

    @Override
    public List<HuespedDTO> getAllHuespedes() {
        return huespedDAO.getAllHuespedes();
    }

    @Override
    public HuespedDTO actualizarHuesped(Long id, ActulizarHuespedDTO actulizarHuespedDTO) {
        log.info("Actualizar datos de huesped con ID{}",id);
        if(!huespedDAO.findById(id).isPresent()){
            log.warn("Intento de actualizar huesped no registrado");
            throw new RuntimeException("Huesped no registrado");
        }
        //**Validaciones de Negocio**
        validarActulizarHuesped(actulizarHuespedDTO);

        HuespedDTO actualizarHuesped=huespedDAO.actualizar(id,actulizarHuespedDTO)
                .orElseThrow(()->new RuntimeException("Error al actualizar huesped"));
        log.info("Huesped actualizado con exito ID: {}",id);
        return actualizarHuesped;
    }

    @Override
    public void eliminarHuesped(Long id) {

    }

    private void validarCrearHuesped(CrearHuespetDTO crearHuespetDTO){
        if(crearHuespetDTO.getNombre()==null || crearHuespetDTO.getNombre().trim().isEmpty()){
            throw new IllegalArgumentException("El nombre del huesped es obligatorio");
        }
        if(!validarEmail(crearHuespetDTO.getEmail())){
            throw new IllegalArgumentException("El email no cumple con el formato");
        }

        if(crearHuespetDTO.getFechaNacimiento()==null){
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria");
        }
    }

    private void validarActulizarHuesped(ActulizarHuespedDTO actulizarHuespedDTO){
        if(actulizarHuespedDTO.getNombre()==null || actulizarHuespedDTO.getNombre().trim().isEmpty()){
            throw new IllegalArgumentException("El nombre actualizado es obligatori");
        }
        if(actulizarHuespedDTO.getCelular()==null || actulizarHuespedDTO.getCelular().trim().isEmpty()){
            throw new IllegalArgumentException("El numero de celular es obligatorio");
        }
    }

    private boolean validarEmail(String emial){
        return emial.contains("@") && emial.contains(".");
    }

}
