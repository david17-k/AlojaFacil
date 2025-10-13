package com.UQ.AlojaFacil.Persistencia.mapper;


import com.UQ.AlojaFacil.Negocio.Servicio.AnfitrionServicio;
import com.UQ.AlojaFacil.Negocio.dto.CrearInmuebleDTO;
import com.UQ.AlojaFacil.Negocio.dto.InmuebleDTO;
import com.UQ.AlojaFacil.Persistencia.entity.AnfitrionEntity;
import com.UQ.AlojaFacil.Persistencia.entity.InmuebleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import javax.swing.*;
import java.util.List;


@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.WARN)
public interface InmuebleMapper {


    @Mapping(target = "idAnfitrion",     source = "anfitrionEntity.id")
    @Mapping(target = "nombreAnfitrion", source = "anfitrionEntity.nombre")
    @Mapping(target = "emailAnfitrion",  source = "anfitrionEntity.email")
    InmuebleDTO toTDO(InmuebleEntity entity);


    @Mapping(target = "id",ignore = true)
    @Mapping(target ="anfitrionEntity",source = "idAnfitrion",qualifiedByName = "createAnfitrionEntityFromId")
    InmuebleEntity toEntity(CrearInmuebleDTO crearInmuebleDTO);

    List<InmuebleDTO>toTDO(List<InmuebleEntity>entities);

    @Named("createAnfitrionEntityFromId")
    default AnfitrionEntity createAnfitrionEntityFromId(Long idAnfitrion){
        if(idAnfitrion==null){
            return null;
        }
        AnfitrionEntity anfitrionEntity=new AnfitrionEntity();
        anfitrionEntity.setId(idAnfitrion);
        return anfitrionEntity;
    }
}
