package com.UQ.AlojaFacil.Persistencia.Repositorio;


import com.UQ.AlojaFacil.Persistencia.entity.ComentarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComentarioRepository extends JpaRepository<ComentarioEntity,Long> {

    Optional<ComentarioEntity>findById(Long id);

    boolean existsByInmuebleEntity_Id(Long idInmueble);

    boolean existsByHuespedEntity_Id(Long idHuesped);
}
