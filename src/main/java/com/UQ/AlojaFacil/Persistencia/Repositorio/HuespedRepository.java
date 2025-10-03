package com.UQ.AlojaFacil.Persistencia.Repositorio;

import com.UQ.AlojaFacil.Modelo.Huesped;
import com.UQ.AlojaFacil.Persistencia.entity.HuespedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HuespedRepository extends JpaRepository<HuespedEntity,Long> {
        List<HuespedEntity> findByNombre(String nombre);

}
