package oscar.funkosspringboot3.common;

import org.springframework.http.ResponseEntity;
import oscar.funkosspringboot3.Funkos.dto.FunkoDto;

import java.util.List;

public interface Controller<T, I> {
    ResponseEntity<List<T>> getAll();

    ResponseEntity<T> getById(I id);

    ResponseEntity<T> create(FunkoDto entity);

    ResponseEntity<T> update(I id, FunkoDto entity);

    ResponseEntity<Void> delete(I id);
}