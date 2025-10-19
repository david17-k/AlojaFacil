package com.UQ.AlojaFacil.Persistencia.mapper;


import com.UQ.AlojaFacil.Negocio.dto.ComentarioDTO;
import com.UQ.AlojaFacil.Negocio.dto.PublicarComentarioDTO;
import com.UQ.AlojaFacil.Persistencia.entity.ComentarioEntity;
import com.UQ.AlojaFacil.Persistencia.entity.HuespedEntity;
import com.UQ.AlojaFacil.Persistencia.entity.InmuebleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.WARN)
public interface ComentarioMapper {

    @Mapping(target ="idHuesped",source = "huespedEntity.id")
    @Mapping(target = "nombreHuesped",source = "huespedEntity.nombre")


    //***Mapping Inmueble***
    @Mapping(target = "idInmueble",source = "inmuebleEntity.id")
    ComentarioDTO toDTO(ComentarioEntity comentarioEntity);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "fechaComentario",ignore = true)
    @Mapping(target = "huespedEntity",source = "idHuesped",qualifiedByName = "createHuespedEntityFromId")
    @Mapping(target = "inmuebleEntity",source = "idInmueble",qualifiedByName ="createInmubleEntityFromId")
    ComentarioEntity toEntity(PublicarComentarioDTO publicarComentarioDTO);

    @Named("createHuespedEntityFromId")
    default HuespedEntity createHuespedEntityFromId(Long idHuesped){
        if(idHuesped==null){
            return null;
        }
        HuespedEntity huesped=new HuespedEntity();
        huesped.setId(idHuesped);
        return huesped;
    }
    @Named("createInmubleEntityFromId")
    default InmuebleEntity createInmubleEntityFromId(Long idInmueble){
        if(idInmueble==null){
            return null;
        }
        InmuebleEntity inmueble=new InmuebleEntity();
        inmueble.setId(idInmueble);
        return inmueble;
    }



}
