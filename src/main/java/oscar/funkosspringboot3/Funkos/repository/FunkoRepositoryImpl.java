package oscar.funkosspringboot3.Funkos.repository;

import org.springframework.stereotype.Repository;
import oscar.funkosspringboot3.Funkos.models.Funko;

import java.util.*;

@Repository
public class FunkoRepositoryImpl implements FunkoRepository {
    private final Map<UUID, Funko> funkos = new HashMap<>();

    @Override
    public Funko save(Funko f) {
        funkos.put(f.getId(), f);
        return f;
    }

    @Override
    public Funko findById(UUID id) {
        return funkos.get(id);
    }

    @Override
    public List<Funko> findAll() {
        return new ArrayList<>(funkos.values());
    }

    @Override
    public Funko deleteById(UUID id) {
        return funkos.remove(id);
    }
}