package com.UQ.AlojaFacil.Persistencia.mapper;


import com.UQ.AlojaFacil.Negocio.dto.CrearHuespetDTO;
import com.UQ.AlojaFacil.Negocio.dto.HuespedDTO;
import com.UQ.AlojaFacil.Persistencia.entity.HuespedEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.WARN)

public interface HuespedMapper {
    HuespedDTO toDTO(HuespedEntity entity);

    List<HuespedDTO>toDTOList(List<HuespedEntity>entities);

    @Mapping(target = "id",ignore = true)
  //  @Mapping(target = "contraseña",ignore = true)
    @Mapping(target = "fechaCreacionRegistro", ignore = true)
    HuespedEntity toEntity(CrearHuespetDTO crearHuespetDTO);



}
