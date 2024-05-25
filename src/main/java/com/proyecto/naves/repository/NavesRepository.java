package com.proyecto.naves.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.naves.model.Naves;

@Repository
public interface NavesRepository extends JpaRepository<Naves, Integer> {

	List<Naves> findByNombreContaining(String nombre);

}
