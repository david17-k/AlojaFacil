package com.UQ.AlojaFacil.Persistencia.Repositorio;


import com.UQ.AlojaFacil.Persistencia.entity.AnfitrionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AnfitrionRepository extends JpaRepository<AnfitrionEntity,Long> {

        Optional<AnfitrionEntity>findById(Long id);

        Optional<AnfitrionEntity>findByEmail(String email);

        Optional<AnfitrionEntity>findByNombre(String nombre);

        boolean existsByEmail(String email);

        boolean existsById(Long id);

        @Query("SELECT COUNT(p) FROM InmuebleEntity p WHERE p.anfitrionEntity.id = :anfitrionId")
        Long countInmuebleByAnfitrionId(@Param("anfitrionId") Long anfitrionId);





}
