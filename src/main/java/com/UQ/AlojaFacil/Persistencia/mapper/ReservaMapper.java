package com.UQ.AlojaFacil.Persistencia.mapper;

import com.UQ.AlojaFacil.Negocio.dto.CrearReservaDTO;
import com.UQ.AlojaFacil.Negocio.dto.ReservaDTO;
import com.UQ.AlojaFacil.Persistencia.entity.HuespedEntity;
import com.UQ.AlojaFacil.Persistencia.entity.InmuebleEntity;
import com.UQ.AlojaFacil.Persistencia.entity.ReservaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.WARN)
public interface ReservaMapper {

    @Mapping(target ="idHuesped",source = "huespedEntity.id")
    @Mapping(target = "nombreHuesped",source = "huespedEntity.nombre")
    @Mapping(target = "emailHuesped",source = "huespedEntity.email")

    //***Mapping Inmueble***
    @Mapping(target = "idInmueble",source = "inmuebleEntity.id")
    @Mapping(target = "numPermitido",source = "inmuebleEntity.numPersonas")
    @Mapping(target = "precioNoche",source = "inmuebleEntity.precioNoche")
    ReservaDTO toDTO(ReservaEntity entity);



    @Mapping(target = "id",ignore = true)
    @Mapping(target = "estado",ignore = true)
    @Mapping(target = "huespedEntity",source = "idHuesped",qualifiedByName="createHuespedEntityFromId")
    @Mapping(target = "inmuebleEntity",source = "idInmueble",qualifiedByName = "createInmuebleEntityFromId")
    ReservaEntity toEntity(CrearReservaDTO crearReservaDTO);

    @Named("createHuespedEntityFromId")
    default HuespedEntity createHuespedEntityFromId(Long idHuesped){
        if(idHuesped==null){
            return null;
        }
        HuespedEntity huesped=new HuespedEntity();
        huesped.setId(idHuesped);
        return huesped;
    }
    @Named("createInmuebleEntityFromId")
    default InmuebleEntity createInmuebleEntityFromId(Long idInmueble){
        if(idInmueble==null){
            return null;
        }
        InmuebleEntity inmueble=new InmuebleEntity();
        inmueble.setId(idInmueble);
        return inmueble;
    }



}
