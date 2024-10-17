package oscar.funkosspringboot3.Funkos.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import oscar.funkosspringboot3.Funkos.models.Funko;
import oscar.funkosspringboot3.Funkos.repository.FunkoRepositoryImpl;

import java.util.Collections;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FunkoController.class)
class FunkoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FunkoRepositoryImpl repository;

    private Funko funko;

    @BeforeEach
    void setUp() {
        funko = new Funko();
        funko.setId(UUID.randomUUID());
        funko.setNombre("Funko Pop");
        funko.setTipo("Figura");
        funko.setPrecio(19.99);
    }

    @Test
    void testGetAll() throws Exception {
        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(funko));

        mockMvc.perform(get("/funkos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value(funko.getNombre()));
    }

    @Test
    void testGetById() throws Exception {
        Mockito.when(repository.findById(funko.getId())).thenReturn(funko);

        mockMvc.perform(get("/funkos/{id}", funko.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value(funko.getNombre()));
    }

    @Test
    void testCreate() throws Exception {
        Mockito.when(repository.save(Mockito.any(Funko.class))).thenReturn(funko);

        mockMvc.perform(post("/funkos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"Funko Pop\", \"tipo\": \"Figura\", \"precio\": 19.99}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value(funko.getNombre()));
    }

    @Test
    void testDelete() throws Exception {
        Mockito.when(repository.deleteById(funko.getId())).thenReturn(funko);

        mockMvc.perform(delete("/funkos/{id}", funko.getId()))
                .andExpect(status().isNoContent());
    }
}
