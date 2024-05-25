package com.proyecto.naves.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.proyecto.naves.dto.NavesPostRequest;
import com.proyecto.naves.dto.NavesPutRequest;
import com.proyecto.naves.model.Naves;

@Component
public class NavesMappers {
	
	
	
	public Naves mapperNavesPutRequestToNaves(NavesPutRequest navesPutRequest, Naves nave) {
		nave.setNombre(navesPutRequest.getNombre());
		nave.setFechaModif(LocalDate.now());
		return nave;
	}
	
	public Naves mapperNavesPostRequestToNaves(NavesPostRequest navesPostRequest) {
		Naves nave = new Naves();
		nave.setFechaAlta(LocalDate.now());
		nave.setNombre(navesPostRequest.getNombre());
		return nave;
	}


}
