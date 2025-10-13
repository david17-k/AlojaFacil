package com.UQ.AlojaFacil.Negocio.Servicio.impl;

import com.UQ.AlojaFacil.Negocio.Servicio.AnfitrionServicio;
import com.UQ.AlojaFacil.Negocio.Servicio.InmuebleServicio;
import com.UQ.AlojaFacil.Negocio.dto.CrearInmuebleDTO;
import com.UQ.AlojaFacil.Negocio.dto.InmuebleDTO;
import com.UQ.AlojaFacil.Persistencia.dao.InmuebleDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class InmuebleServicioImpl implements InmuebleServicio {

    private final InmuebleDAO inmuebleDAO;
    private final AnfitrionServicio anfitrionServicio;

    @Override
    public InmuebleDTO crearInmueble(CrearInmuebleDTO crearInmuebleDTO) {
        log.info("Creando nuevo alojamiento {}",crearInmuebleDTO.getTitulo());
        anfitrionServicio.getAnfitrionById(crearInmuebleDTO.getIdAnfitrion());
        InmuebleDTO creaAlojamiento=inmuebleDAO.save(crearInmuebleDTO);
        log.info("Alojamiento creado con exito- NOMBRE ANFITRION {}",creaAlojamiento.getNombreAnfitrion());

        return inmuebleDAO.findByWithAnfitrion(creaAlojamiento.getId()).orElseThrow(()
                ->new RuntimeException("Error al cargar anfitrion"));
    }

    @Override
    @Transactional(readOnly = true)
    public InmuebleDTO getInmuebleId(Long id) {
        log.info("Buscando inmueble con ID{}",id);
        return inmuebleDAO.findByid(id).orElseThrow(()->{
                log.warn("No se encontro el ID{}",id);
               return new RuntimeException("El id no se encontro");});
    }

    @Override
    public List<InmuebleDTO> getAllInmuebles() {
        return List.of();
    }
}
