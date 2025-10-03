package com.UQ.AlojaFacil.Negocio.Servicio.impl;

import com.UQ.AlojaFacil.Negocio.Servicio.HuespedServicio;
import com.UQ.AlojaFacil.Negocio.dto.CrearHuespetDTO;
import com.UQ.AlojaFacil.Negocio.dto.HuespedDTO;
import com.UQ.AlojaFacil.Persistencia.dao.HuespedDAO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class HuespedServicioImpl implements HuespedServicio {

    private final HuespedDAO huespedDAO;
    @Override
    //Falta valiadar email;
    public HuespedDTO crearHuesped(CrearHuespetDTO crearHuespetDTO) {
     log.info("Creando nuevo vendedor con email:{}",crearHuespetDTO.getEmail());
     HuespedDTO creaHuesped=huespedDAO.save(crearHuespetDTO);
     log.info("Huesped crado exitosamente con ID:{}", creaHuesped.getId());
     return creaHuesped;

    }

}
