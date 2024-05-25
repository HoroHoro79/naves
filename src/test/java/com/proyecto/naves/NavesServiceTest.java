package com.proyecto.naves;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.naves.dto.NavesPostRequest;
import com.proyecto.naves.dto.NavesPutRequest;
import com.proyecto.naves.exception.ResourceNotFoundException;
import com.proyecto.naves.mapper.NavesMappers;
import com.proyecto.naves.model.Naves;
import com.proyecto.naves.repository.NavesRepository;
import com.proyecto.naves.service.NavesService;

@ExtendWith(MockitoExtension.class)
class NavesServiceTest {

    @Mock
    private NavesRepository navesRepository;

    @Mock
    private NavesMappers navesMapper;

    @InjectMocks
    private NavesService navesService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetNaveById() {
        int id = 1;
        Naves nave = new Naves();
        nave.setId(1);
        nave.setNombre("Millennium Falcon");
        when(navesRepository.findById(id)).thenReturn(Optional.of(nave));
        Optional<Naves> result = navesService.getNaveById(id);
        assertTrue(result.isPresent());
        assertEquals(nave, result.get());
    }

    @Test
    void testGetNaveByIdNoExist() {
        int id = 1;
        assertThrows(ResourceNotFoundException.class, () -> {
        	navesService.getNaveById(id);
        });
    }

    @Test
    void testGetNaves() {
        List<Naves> navesList = List.of(new Naves(), new Naves());
        when(navesRepository.findAll()).thenReturn(navesList);
        List<Naves> result = navesService.getNaves();
        assertEquals(navesList.size(), result.size());
        assertTrue(result.containsAll(navesList));
    }

    @Test
    void testPutNave() {
        NavesPutRequest navePutRequest = new NavesPutRequest();
        navePutRequest.setId(1);
        navePutRequest.setNombre("Slave I");
        Naves naveBBDD = new Naves();
        naveBBDD.setNombre(navePutRequest.getNombre());
        naveBBDD.setId(navePutRequest.getId());
        when(navesRepository.findById(navePutRequest.getId())).thenReturn(Optional.of(naveBBDD));
        when(navesMapper.mapperNavesPutRequestToNaves(navePutRequest, naveBBDD)).thenReturn(naveBBDD);
        when(navesRepository.save(naveBBDD)).thenReturn(naveBBDD);
        Naves result = navesService.putNave(navePutRequest);
        assertEquals(naveBBDD, result);
    }

    @Test
    void testPutNaveNonExistingNave() {
        NavesPutRequest navePutRequest = new NavesPutRequest();
        navePutRequest.setId(1);
        when(navesRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            navesService.putNave(navePutRequest);
        });
    }

    @Test
    void testPostNave() {
        NavesPostRequest naveRequest = new NavesPostRequest();
        naveRequest.setNombre("X Wings");
        Naves naveSave = new Naves();
        naveSave.setNombre(naveRequest.getNombre());
       when(navesMapper.mapperNavesPostRequestToNaves(naveRequest)).thenReturn(naveSave);
        when(navesRepository.save(naveSave)).thenReturn(naveSave);
        Naves result = navesService.postNave(naveRequest);
        assertTrue(result != null);
    }

    @Test
    void testGetNavesPageable() {
        Page<Naves> page = mock(Page.class);
        when(navesRepository.findAll(any(Pageable.class))).thenReturn(page);
        Page<Naves> result = navesService.getNaves(mock(Pageable.class));
        assertEquals(page, result);
    }

    @Test
    void testDeleteNaveById() {
        int id = 1;
        Naves naveExistente = new Naves();
        when(navesRepository.findById(id)).thenReturn(Optional.of(naveExistente));
        navesService.deleteNaveById(id);
        verify(navesRepository).deleteById(id);
    }

    @Test
    void testDeleteNaveByIdNonExistingNave() {
        int id = 1;
        assertThrows(ResourceNotFoundException.class, () -> {
            navesService.deleteNaveById(id);
        });
    }
    @Test
    void testGetByFiltros() {
        String nombre = "nombre";
        List<Naves> navesList = List.of(new Naves(), new Naves());
        when(navesRepository.findByNombreContaining(nombre)).thenReturn(navesList);

        List<Naves> result = navesService.getByFiltros(nombre);

        assertEquals(navesList.size(), result.size());
        assertTrue(result.containsAll(navesList));
    }
}
