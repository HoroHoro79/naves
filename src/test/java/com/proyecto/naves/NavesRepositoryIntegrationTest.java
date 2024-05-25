package com.proyecto.naves;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.naves.model.Naves;
import com.proyecto.naves.repository.NavesRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class NavesRepositoryIntegrationTest {
	  @Autowired
	    private NavesRepository navesRepository;
	  
	    @BeforeEach
	    public void setUp() {
	        Naves nave = new Naves();
	        nave.setNombre("X Wings");
	        nave.setFechaAlta(LocalDate.now());
	        navesRepository.save(nave);
	    }



	    @Test
	    @Transactional
	    public void testFindByNombreContaining() {
	        String nombreABuscar = "Wings";
	        List<Naves> navesEncontradas = navesRepository.findByNombreContaining(nombreABuscar);
	        assertNotNull(navesEncontradas); 
	    }
}
