package com.proyecto.naves.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proyecto.naves.dto.NavesPostRequest;
import com.proyecto.naves.dto.NavesPutRequest;
import com.proyecto.naves.mapper.NavesMappers;
import com.proyecto.naves.model.Naves;
import com.proyecto.naves.repository.NavesRepository;

@Service
public class NavesService {

	@Autowired
	private NavesRepository navesRepository;

	@Autowired
	private  NavesMappers navesMapper;


	public Optional<Naves> getNaveById(Integer id) {
		Optional<Naves> nave = navesRepository.findById(id);
		if (nave.isPresent()) {
			return nave;
		}
		return null;
	}

	public List<Naves> getNaves() {
		return navesRepository.findAll();
	}

	public Naves save(Naves nave2) {
		return navesRepository.save(nave2);
	}

	public Naves putNave(@Valid NavesPutRequest navePutRequest) {
		Optional<Naves> naveBBDD = navesRepository.findById(navePutRequest.getId());
		if (naveBBDD.isPresent()) {
			Naves naveUpdate = navesMapper.mapperNavesPutRequestToNaves(navePutRequest, naveBBDD.get());
		return navesRepository.save(naveUpdate);
		}
		return null;
	}

	public Naves postNave(@Valid NavesPostRequest naveRequest) {
		Naves naveSave = navesMapper.mapperNavesPostRequestToNaves(naveRequest);
		return navesRepository.save(naveSave);
	}

	public Page<Naves> getNaves(Pageable pageable) {
		 return navesRepository.findAll(pageable);
	}

	public boolean deleteNaveById(Integer id) {
		  if (navesRepository.existsById(id)) {
	            navesRepository.deleteById(id);
	            return true;
	        } else {
	            return false;
	        }
	}

	public List<Naves> getByFiltros(String nombre) {
		return navesRepository.findByNombreContaining(nombre);
	}


}
