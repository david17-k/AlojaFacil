package com.UQ.AlojaFacil.Persistencia.Repositorio;

import com.UQ.AlojaFacil.Persistencia.entity.InmuebleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InmuebleRepository extends JpaRepository<InmuebleEntity,Long> {

    Optional<InmuebleEntity> findById(Long id);

}
