package com.proyecto.naves.dto;



import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class NavesPostRequest {
	
	 @Schema(description = "Nombre de la nave", example = "Ghost")
	   @NotNull(message = "El campo 'nombre' no se ha informado")
	    private String nombre;
}
