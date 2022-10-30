package utc.edu.hongheo.service;

import utc.edu.hongheo.model.Category;

import java.util.Optional;

public interface IService<T> {
    T save(T t);

    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void delete(Long id);

}
