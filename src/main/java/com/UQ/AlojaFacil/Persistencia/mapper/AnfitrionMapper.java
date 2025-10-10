package com.UQ.AlojaFacil.Persistencia.mapper;


import com.UQ.AlojaFacil.Negocio.dto.ActualizarAnfitrionDTO;
import com.UQ.AlojaFacil.Negocio.dto.AnfitrionDTO;
import com.UQ.AlojaFacil.Negocio.dto.CrearAnfitrionDTO;
import com.UQ.AlojaFacil.Persistencia.entity.AnfitrionEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.WARN)
public interface AnfitrionMapper {
    AnfitrionDTO toDTO(AnfitrionEntity entity);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "inmuebles",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    AnfitrionEntity toEntity(CrearAnfitrionDTO crearAnfitrionDTO);

    List<AnfitrionDTO>toDTOList(List<AnfitrionEntity>anfitrionEntities);

    //***Actualizar Anfitrion***
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "email",ignore = true)
    @Mapping(target = "inmuebles",ignore = true)
    @Mapping(target = "fechaNacimiento",ignore = true)
    @Mapping(target = "contraseña",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void actualiarEntityFromDTO(ActualizarAnfitrionDTO actualizarAnfitrionDTO,@MappingTarget AnfitrionEntity entity);

}
