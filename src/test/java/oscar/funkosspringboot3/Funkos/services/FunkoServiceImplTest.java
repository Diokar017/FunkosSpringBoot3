package oscar.funkosspringboot3.Funkos.services;

import oscar.funkosspringboot3.Funkos.exceptions.FunkoNotFoundException;
import oscar.funkosspringboot3.Funkos.models.Funko;
import oscar.funkosspringboot3.Funkos.repository.FunkoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FunkoServiceImplTest {

    private FunkoRepository funkoRepository;
    private FunkoServiceImpl funkoService;
    private Funko funko;

    @BeforeEach
    void setUp() {
        funkoRepository = Mockito.mock(FunkoRepository.class);
        funkoService = new FunkoServiceImpl(funkoRepository);
        funko = new Funko();
        funko.setId(UUID.randomUUID());
        funko.setNombre("Funko Pop");
        funko.setTipo("Figura");
        funko.setPrecio(19.99);
    }

    @Test
    void testGetAllFunkos() {
        when(funkoRepository.findAll()).thenReturn(Collections.singletonList(funko));
        var result = funkoService.getAllFunkos();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(funko.getNombre(), result.get(0).getNombre());
    }

    @Test
    void testGetFunkoById_Success() {
        when(funkoRepository.findById(funko.getId())).thenReturn(funko);
        Funko result = funkoService.getFunkoById(funko.getId());
        assertNotNull(result);
        assertEquals(funko.getNombre(), result.getNombre());
    }

    @Test
    void testGetFunkoById_NotFound() {
        when(funkoRepository.findById(any(UUID.class))).thenReturn(null);
        FunkoNotFoundException exception = assertThrows(FunkoNotFoundException.class, () -> {
            funkoService.getFunkoById(UUID.randomUUID());
        });

        assertEquals("Funko con id: " + UUID.randomUUID() + " no encontrado", exception.getMessage());
    }

    @Test
    void testCreateFunko() {
        when(funkoRepository.save(any(Funko.class))).thenReturn(funko);
        Funko result = funkoService.createFunko(funko);
        assertNotNull(result);
        assertEquals(funko.getNombre(), result.getNombre());
        verify(funkoRepository, times(1)).save(funko);
    }

    @Test
    void testUpdateFunko_Success() {
        when(funkoRepository.findById(funko.getId())).thenReturn(funko);
        when(funkoRepository.save(any(Funko.class))).thenReturn(funko);
        Funko updatedFunko = new Funko();
        updatedFunko.setNombre("Updated Funko");
        updatedFunko.setTipo("Figura");
        updatedFunko.setPrecio(25.00);
        Funko result = funkoService.updateFunko(funko.getId(), updatedFunko);
        assertNotNull(result);
        assertEquals(updatedFunko.getNombre(), result.getNombre());
        verify(funkoRepository, times(1)).save(any(Funko.class));
    }

    @Test
    void testUpdateFunko_NotFound() {
        // Simulando el comportamiento del repositorio
        when(funkoRepository.findById(any(UUID.class))).thenReturn(null);
        FunkoNotFoundException exception = assertThrows(FunkoNotFoundException.class, () -> {
            funkoService.updateFunko(funko.getId(), funko);
        });
        assertEquals("Funko con id: " + funko.getId() + " no encontrado", exception.getMessage());
    }

    @Test
    void testDeleteFunkoById_Success() {
        when(funkoRepository.findById(funko.getId())).thenReturn(funko);
        doNothing().when(funkoRepository).deleteById(funko.getId());
        assertDoesNotThrow(() -> {
            funkoService.deleteFunkoById(funko.getId());
        });
        verify(funkoRepository, times(1)).deleteById(funko.getId());
    }

    @Test
    void testDeleteFunkoById_NotFound() {
        when(funkoRepository.findById(any(UUID.class))).thenReturn(null);
        FunkoNotFoundException exception = assertThrows(FunkoNotFoundException.class, () -> {
            funkoService.deleteFunkoById(funko.getId());
        });
        assertEquals("Funko con id: " + funko.getId() + " no encontrado", exception.getMessage());
    }
}
