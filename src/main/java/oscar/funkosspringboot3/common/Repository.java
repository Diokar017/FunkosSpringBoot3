package oscar.funkosspringboot3.common;

import java.util.List;

public interface Repository<T, I> {

    T save(T entity);

    T findById(I id);

    List<T> findAll();

    T deleteById(I id);
}
