package com.proyecto.naves.dto;



import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class NavesPutRequest {
	
	  @Schema(description = "ID de la nave", example = "1")
	   @NotNull(message = "El campo 'id' no se ha informado")
	    private Integer id;
	    
	  @Schema(description = "Nombre de la nave", example = "Millenium Falcon")
	   @NotNull(message = "El campo 'nombre' no se ha informado")
	    private String nombre;
}
