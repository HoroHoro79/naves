package com.proyecto.naves.dto;



import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class NavesPutRequest {
	
	   @NotNull(message = "El campo 'id' no se ha informado")
	    private Integer id;
	    
	   @NotNull(message = "El campo 'nombre' no se ha informado")
	    private String nombre;
}
