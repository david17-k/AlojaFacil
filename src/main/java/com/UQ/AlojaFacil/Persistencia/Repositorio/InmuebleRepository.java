package com.UQ.AlojaFacil.Persistencia.Repositorio;

import com.UQ.AlojaFacil.Persistencia.entity.InmuebleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InmuebleRepository extends JpaRepository<InmuebleEntity,Long> {

    Optional<InmuebleEntity> findById(Long id);

    boolean existsById(Long id);

    @Query("SELECT i FROM InmuebleEntity i JOIN FETCH i.anfitrionEntity WHERE i.id = :id")
    Optional<InmuebleEntity>findByWithAnfitrion(@Param("id") Long id);
}
