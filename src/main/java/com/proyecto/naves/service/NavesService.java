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
import com.proyecto.naves.exception.ResourceNotFoundException;
import com.proyecto.naves.mapper.NavesMappers;
import com.proyecto.naves.model.Naves;
import com.proyecto.naves.repository.NavesRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class NavesService {

    @Autowired
    private NavesRepository navesRepository;

    @Autowired
    private NavesMappers navesMapper;


    public Optional<Naves> getNaveById(Integer id) {
        log.info("Entrando en getNaveById con id: {}", id);
        return Optional.ofNullable(navesRepository.findById(id).orElseThrow(() -> {
            log.error("Recurso no encontrado para este id: {}", id);
            return new ResourceNotFoundException("Recurso no encontrado para este id :: " + id);
        }));
    }

    public List<Naves> getNaves() {
        log.info("Entrando en getNaves");
        List<Naves> naves = navesRepository.findAll();
        if (naves.isEmpty()) {
            log.error("No se encontraron naves");
            throw new ResourceNotFoundException("Naves no encontradas");
        }
        return naves;
    }

    public Naves putNave(@Valid NavesPutRequest navePutRequest) {
        log.info("Entrando en putNave con id: {}", navePutRequest.getId());
        Optional<Naves> naveBBDD = Optional.ofNullable(navesRepository.findById(navePutRequest.getId()).orElseThrow(() -> {
            log.error("No existe el recurso con id: {}", navePutRequest.getId());
            return new ResourceNotFoundException("No existe el recurso con id :: " + navePutRequest.getId());
        }));
        Naves naveUpdate = navesMapper.mapperNavesPutRequestToNaves(navePutRequest, naveBBDD.get());
        return navesRepository.save(naveUpdate);
    }

    public Naves postNave(@Valid NavesPostRequest naveRequest) {
        log.info("Entrando en postNave");
        Naves naveSave = navesMapper.mapperNavesPostRequestToNaves(naveRequest);
        return navesRepository.save(naveSave);
    }

    public Page<Naves> getNaves(Pageable pageable) {
        log.info("Entrando en getNaves con pageable: {}", pageable);
        Page<Naves> navesPage = navesRepository.findAll(pageable);
        if (navesPage.isEmpty()) {
            log.error("No se encontraron naves");
            throw new ResourceNotFoundException("Naves no encontradas");
        }
        return navesPage;
    }

    public void deleteNaveById(Integer id) {
        log.info("Entrando en deleteNaveById con id: {}", id);
        Optional.ofNullable(navesRepository.findById(id).orElseThrow(() -> {
            log.error("No existe el recurso con id: {}", id);
            return new ResourceNotFoundException("No existe el recurso con id :: " + id);
        }));
        navesRepository.deleteById(id);
    }

    public List<Naves> getByFiltros(String nombre) {
        log.info("Entrando en getByFiltros con nombre: {}", nombre);
        List<Naves> naves = navesRepository.findByNombreContaining(nombre);
        if (naves.isEmpty()) {
            log.error("No se encontraron naves con nombre: {}", nombre);
            throw new ResourceNotFoundException("Naves no encontradas");
        }
        return naves;
    }
}
