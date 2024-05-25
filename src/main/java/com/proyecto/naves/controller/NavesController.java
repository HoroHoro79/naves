package com.proyecto.naves.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.naves.dto.NavesPostRequest;
import com.proyecto.naves.dto.NavesPutRequest;
import com.proyecto.naves.model.Naves;
import com.proyecto.naves.service.NavesService;
import com.proyecto.naves.utils.Constantes;

import lombok.extern.log4j.Log4j2;



@RestController
@RequestMapping(Constantes.REQUEST_MAPPING)
@Log4j2
public class NavesController {

	@Autowired
    private NavesService navesService;
    
    private static final String version = "/v1";
    
    @GetMapping(version+Constantes.STATUS_ENDPOINT)
    public ResponseEntity<Map<String, String>> getStatus() {
        log.info("Entrando en getStatus()");
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        response.put("timestamp", String.valueOf(System.currentTimeMillis()));
        log.info("Saliendo de getStatus()");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping(version+Constantes.NAVE_BY_ID_ENDPOINT)
    @Cacheable(value = "naveCache", key = "#id")
    public ResponseEntity<Naves> getNaveById(@PathVariable Integer id) {
        log.info("Entrando en getNaveById() con id: {}", id);
        Optional<Naves> nave = navesService.getNaveById(id);
        log.info("Saliendo de getNaveById()");
        return ResponseEntity.ok().body(nave.get());
    }

    @GetMapping(version+Constantes.ALL_NAVES_ENDPOINT)
    @Cacheable(value = "allNavesCache")
    public ResponseEntity<List<Naves>> getNaves() {
        log.info("Entrando en getNaves()");
        List<Naves> naves = navesService.getNaves();
        log.info("Saliendo de getNaves()");
        return ResponseEntity.ok().body(naves); 
    }
    
    @GetMapping(version+Constantes.PAGINACION_ENDPOINT)
    public ResponseEntity<List<Naves>> getNavesPage(@PageableDefault(size = 10) Pageable pageable) {
        log.info("Entrando en getNavesPage() con pageable: {}", pageable);
        Page<Naves> salida = navesService.getNaves(pageable);
        log.info("Saliendo de getNavesPage()");
        return ResponseEntity.ok(salida.get().toList()); 
    }
    
    @GetMapping(version+Constantes.FILTROS_ENDPOINT)
    @Cacheable(value = "navesaByFiltrosCache", key = "#nombre")
    public ResponseEntity<List<Naves>> getByFiltros(@RequestParam String nombre) {
        log.info("Entrando en getByFiltros() con nombre: {}", nombre);
        List<Naves> naves = navesService.getByFiltros(nombre);
        log.info("Saliendo de getByFiltros()");
        return ResponseEntity.ok().body(naves); 
    }
    
    @PutMapping(version+Constantes.PUT_NAVE_ENDPOINT)
    public ResponseEntity<Naves> putNave(@Valid @RequestBody NavesPutRequest naveRequest) {
        log.info("Entrando en putNave() con naveRequest: {}", naveRequest);
        Naves naveUpdated = navesService.putNave(naveRequest);
        log.info("Saliendo de putNave()");
        return ResponseEntity.ok().body(naveUpdated);
    } 
    
    @PostMapping(version+Constantes.POST_NAVE_ENDPOINT)
    public ResponseEntity<Naves> postNave(@Valid @RequestBody NavesPostRequest naveRequest) {
        log.info("Entrando en postNave() con naveRequest: {}", naveRequest);
        Naves navePosted = navesService.postNave(naveRequest);
        log.info("Saliendo de postNave()");
        return ResponseEntity.ok().body(navePosted);
    }
    
    @DeleteMapping(version+Constantes.DELETE_NAVE_ENDPOINT)
    public ResponseEntity<Void> deleteNave(@PathVariable Integer id) {
        log.info("Entrando en deleteNave() con id: {}", id);
        navesService.deleteNaveById(id);
        log.info("Saliendo de deleteNave()");
        return ResponseEntity.ok().body(null);
    }
}
