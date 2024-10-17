package oscar.funkosspringboot3.Funkos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oscar.funkosspringboot3.Funkos.exceptions.FunkoNotFoundException;
import oscar.funkosspringboot3.Funkos.models.Funko;
import oscar.funkosspringboot3.Funkos.repository.FunkoRepository;
import java.util.List;
import java.util.UUID;

@Service
public class FunkoServiceImpl implements FunkoService {

    private final FunkoRepository funkoRepository;

    @Autowired
    public FunkoServiceImpl(FunkoRepository funkoRepository) {
        this.funkoRepository = funkoRepository;
    }

    @Override
    public List<Funko> getAllFunkos() {
        return funkoRepository.findAll();
    }

    @Override
    public Funko getFunkoById(UUID id) throws FunkoNotFoundException {
        Funko funko = funkoRepository.findById(id);
        if (funko == null) {
            throw new FunkoNotFoundException(id);
        }
        return funko;
    }

    @Override
    public Funko createFunko(Funko funko) {
        return funkoRepository.save(funko);
    }

    @Override
    public Funko updateFunko(UUID id, Funko funko) throws FunkoNotFoundException {
        Funko existingFunko = funkoRepository.findById(id);
        if (existingFunko == null) {
            throw new FunkoNotFoundException(id);
        }
        funko.setId(id); // Asegurar que se mantiene el mismo ID
        return funkoRepository.save(funko);
    }

    @Override
    public Funko deleteFunkoById(UUID id) throws FunkoNotFoundException {
        Funko funko = funkoRepository.findById(id);
        if (funko == null) {
            throw new FunkoNotFoundException(id);
        }
        funkoRepository.deleteById(id);
        return funko;
    }
}
