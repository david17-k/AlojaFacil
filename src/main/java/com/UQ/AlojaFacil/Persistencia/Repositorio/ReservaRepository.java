package com.UQ.AlojaFacil.Persistencia.Repositorio;

import com.UQ.AlojaFacil.Persistencia.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReservaRepository extends JpaRepository<ReservaEntity,Long> {

    Optional<ReservaEntity>findById(Long id);

    @Query("SELECT COUNT(p) FROM ReservaEntity p WHERE p.huespedEntity.id = :huespedId")
    Long countReservaByHuespedId(@Param("huespedId") Long anfitrionId);


}
