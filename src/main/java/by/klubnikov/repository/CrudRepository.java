package by.klubnikov.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, D> {
    List<T> findAll();

    Optional<T> findById(D id);

    D save(T entity);

    void update(T entity);

    void delete(T entity);
}
