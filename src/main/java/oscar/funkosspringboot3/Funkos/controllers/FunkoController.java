package oscar.funkosspringboot3.Funkos.controllers;

import oscar.funkosspringboot3.Funkos.exceptions.FunkoNotFoundException;
import oscar.funkosspringboot3.Funkos.mappers.FunkoMapper;
import oscar.funkosspringboot3.Funkos.models.Funko;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oscar.funkosspringboot3.Funkos.dto.FunkoDto;
import oscar.funkosspringboot3.Funkos.services.FunkoService;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/funkos")
public class FunkoController {
    private static final String FUNKO_NOT_FOUND = "Funko con id: {} no encontrado";

    private final FunkoService funkoService;
    private final FunkoMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(FunkoController.class);

    @Autowired
    public FunkoController(FunkoService funkoService, FunkoMapper mapper) {
        this.funkoService = funkoService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<Funko>> getAll() {
        logger.info("Obteniendo todos los Funkos");
        List<Funko> funkos = funkoService.getAllFunkos();
        return ResponseEntity.ok(funkos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funko> getById(@PathVariable UUID id) {
        logger.info("Obteniendo Funko con id: {}", id);
        try {
            Funko funko = funkoService.getFunkoById(id);
            return ResponseEntity.ok(funko);
        } catch (FunkoNotFoundException e) {
            logger.warn(FUNKO_NOT_FOUND, id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Funko> create(@RequestBody FunkoDto funkoDto) {
        logger.info("Creando nuevo Funko: {}", funkoDto);
        Funko funko = mapper.toFunko(funkoDto);
        Funko savedFunko = funkoService.createFunko(funko);
        return ResponseEntity.ok(savedFunko);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funko> update(@PathVariable UUID id, @RequestBody FunkoDto funkoDto) {
        logger.info("Actualizando Funko con id: {}", id);
        try {
            Funko funko = mapper.toFunko(funkoDto);
            Funko updatedFunko = funkoService.updateFunko(id, funko);
            return ResponseEntity.ok(updatedFunko);
        } catch (FunkoNotFoundException e) {
            logger.warn(FUNKO_NOT_FOUND, id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        logger.info("Eliminando Funko con id: {}", id);
        try {
            funkoService.deleteFunkoById(id);
            return ResponseEntity.noContent().build();
        } catch (FunkoNotFoundException e) {
            logger.warn(FUNKO_NOT_FOUND, id);
            return ResponseEntity.notFound().build();
        }
    }
}
