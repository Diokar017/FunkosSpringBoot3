package oscar.funkosspringboot3.Funkos.services;


import oscar.funkosspringboot3.Funkos.models.Funko;

import java.util.List;
import java.util.UUID;

public interface FunkoService {
    List<Funko> getAllFunkos();
    Funko getFunkoById(UUID id);
    Funko createFunko(Funko funko);
    Funko updateFunko(UUID id, Funko funko);
    Funko deleteFunkoById(UUID id);
}