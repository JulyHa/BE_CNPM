package utc.edu.hongheo.service;

import utc.edu.hongheo.model.Category;

import java.util.Optional;

public interface ICategoryService {
    Category save(Category category);

    Iterable<Category> findAll();

    Optional<Category> findById(Long id);

    void delete(Long id);

    Iterable<Category> findAllByStatus(int num, Long id);

    Iterable<Category> findAllByUserId(Long id);
}
