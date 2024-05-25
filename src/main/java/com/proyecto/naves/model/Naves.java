package com.proyecto.naves.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "naves", schema = "public")
@Data
public class Naves {
	
	  @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "naves_seq")
	    @SequenceGenerator(name = "naves_seq", sequenceName = "s_naves", allocationSize = 1)
	    @Column(name = "id", nullable = false)
	    private Integer id;
	    
	    @Column(name = "nombre", nullable = false, length = 50)
	    private String nombre;
	    
	    @Column(name = "fcalta",columnDefinition = "TIMESTAMP")
	    private LocalDate fechaAlta;

	    @Column(name = "fcmodif",columnDefinition = "TIMESTAMP")
	    private LocalDate fechaModif;
	    
	    
}
