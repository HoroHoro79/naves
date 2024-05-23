package com.proyecto.naves.dto;



import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class NavesPostRequest {
	
	   @NotNull(message = "El campo 'nombre' no se ha informado")
	    private String nombre;
}
