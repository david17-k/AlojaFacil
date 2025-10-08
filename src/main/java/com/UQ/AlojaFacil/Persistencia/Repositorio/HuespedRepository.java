package com.UQ.AlojaFacil.Persistencia.Repositorio;

import com.UQ.AlojaFacil.Modelo.Huesped;
import com.UQ.AlojaFacil.Persistencia.entity.HuespedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HuespedRepository extends JpaRepository<HuespedEntity,Long> {
        List<HuespedEntity> findByNombre(String nombre);

        Optional<HuespedEntity>findByEmail(String email);

        Optional<HuespedEntity>findById(Long id);

        boolean existsByEmail(String emial);

        boolean existsById(Long id);



}
