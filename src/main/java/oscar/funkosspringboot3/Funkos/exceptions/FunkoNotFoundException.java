package oscar.funkosspringboot3.Funkos.exceptions;

import java.util.UUID;

public class FunkoNotFoundException extends RuntimeException {
    public FunkoNotFoundException(UUID id) {
        super("Funko con ID: " + id + " no encontrado");
    }
}