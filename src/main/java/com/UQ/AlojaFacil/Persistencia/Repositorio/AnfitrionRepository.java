package com.UQ.AlojaFacil.Persistencia.Repositorio;


import com.UQ.AlojaFacil.Persistencia.entity.AnfitrionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnfitrionRepository extends JpaRepository<AnfitrionEntity,Long> {

        Optional<AnfitrionEntity>findById(Long id);

        Optional<AnfitrionEntity>findByEmail(String email);

        Optional<AnfitrionEntity>findByNombre(String nombre);

        boolean existsByEmail(String email);

        boolean existsById(Long id);



}
