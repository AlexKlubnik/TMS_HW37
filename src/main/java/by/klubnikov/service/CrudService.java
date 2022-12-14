package by.klubnikov.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, D> {
    List<T> findAll();

    Optional<T> findById(D id);

    T save(T entity);

    void delete(T entity);
}
