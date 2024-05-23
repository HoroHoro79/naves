package com.proyecto.naves.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/api/naves")
public class NavesController {

    @Autowired
    private NavesService navesService;

    @GetMapping("/status")
    public ResponseEntity<Map<String, String>> getStatus() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        response.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Naves> getNaveById(@PathVariable Integer id) {
        try {
        Optional<Naves> nave = navesService.getNaveById(id);
        return nave != null ?
                ResponseEntity.status(HttpStatus.OK).body(nave.get()) :
             	   ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (Exception e) {
        	 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    }

    @GetMapping("/")
    public ResponseEntity<List<Naves>> getNaves() {
        List<Naves> salida = navesService.getNaves();
        return !salida.isEmpty() ?
               ResponseEntity.ok(salida) :
               ResponseEntity.notFound().build();
    }
    
    
    @GetMapping("/paginacion")
    public ResponseEntity<Page<Naves>> getNavesPage(@PageableDefault(size = 10) Pageable pageable) {
        Page<Naves> salida = navesService.getNaves(pageable);
        return !salida.isEmpty() ?
               ResponseEntity.ok(salida) :
               ResponseEntity.notFound().build();
    }
    
    @GetMapping("/filtros")
    public ResponseEntity<List<Naves>> getByFiltros(@RequestParam String nombre) {
        List<Naves> naves = navesService.getByFiltros(nombre);
        if (naves.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(naves, HttpStatus.OK);
    }
    
    
    @PutMapping("/")
    public ResponseEntity<Naves> putNave(@Valid @RequestBody NavesPutRequest naveRequest) {
        try {
            Naves naveUpdated = navesService.putNave(naveRequest);
            return naveUpdated != null ?
                   ResponseEntity.status(HttpStatus.ACCEPTED).body(naveUpdated) :
                	   ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping("/")
    public ResponseEntity<Naves> postNave(@Valid @RequestBody NavesPostRequest naveRequest) {
    	   try {
    	Naves navePosted = navesService.postNave(naveRequest);
    	  return navePosted != null ?
                  ResponseEntity.status(HttpStatus.CREATED).body(navePosted) :
                  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       } catch (Exception ex) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNave(@PathVariable Integer id) {
        try {
            if (!navesService.deleteNaveById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
