package oscar.funkosspringboot3.Funkos.repository;

import org.junit.jupiter.api.Test;
import oscar.funkosspringboot3.Funkos.models.Funko;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class FunkoRepositoryImplTest {

    private FunkoRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new FunkoRepositoryImpl();
    }

    @Test
    void testSave() {
        Funko funko = new Funko();
        funko.setNombre("Funko Pop");
        funko.setTipo("Figura");
        funko.setPrecio(15.99);

        Funko savedFunko = repository.save(funko);

        assertNotNull(savedFunko);
        assertNotNull(savedFunko.getId());
        assertEquals(funko.getNombre(), savedFunko.getNombre());
    }

    @Test
    void testFindById() {
        Funko funko = new Funko();
        repository.save(funko);

        Funko foundFunko = repository.findById(funko.getId());

        assertNotNull(foundFunko);
        assertEquals(funko.getId(), foundFunko.getId());
    }

    @Test
    void testFindAll() {
        Funko funko1 = new Funko();
        Funko funko2 = new Funko();
        repository.save(funko1);
        repository.save(funko2);

        assertEquals(2, repository.findAll().size());
    }

    @Test
    void testDeleteById() {
        Funko funko = new Funko();
        repository.save(funko);

        Funko deletedFunko = repository.deleteById(funko.getId());

        assertNotNull(deletedFunko);
        assertNull(repository.findById(funko.getId()));  // Verificar que fue eliminado
    }
}