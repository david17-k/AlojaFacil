package com.UQ.AlojaFacil.Persistencia.mapper;


import com.UQ.AlojaFacil.Negocio.dto.ActulizarHuespedDTO;
import com.UQ.AlojaFacil.Negocio.dto.CrearHuespetDTO;
import com.UQ.AlojaFacil.Negocio.dto.HuespedDTO;
import com.UQ.AlojaFacil.Persistencia.entity.HuespedEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.WARN)

public interface HuespedMapper {
    HuespedDTO toDTO(HuespedEntity entity);

    List<HuespedDTO>toDTOList(List<HuespedEntity>entities);

    @Mapping(target = "id",ignore = true)
  //  @Mapping(target = "contraseña",ignore = true)
    @Mapping(target = "fechaCreacionRegistro", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "accountNonExpired", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    @Mapping(target = "credentialsNonExpired", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    HuespedEntity toEntity(CrearHuespetDTO crearHuespetDTO);


    //**Mapping Actualizar huesped**
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "email",ignore = true)
    @Mapping(target = "contraseña",ignore = true)
    @Mapping(target = "fechaCreacionRegistro",ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "accountNonExpired", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    @Mapping(target = "credentialsNonExpired", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy  = NullValuePropertyMappingStrategy.IGNORE)
    void actualizarEntiyFromDTO(ActulizarHuespedDTO actualizarDTO,@MappingTarget HuespedEntity entity );


}
