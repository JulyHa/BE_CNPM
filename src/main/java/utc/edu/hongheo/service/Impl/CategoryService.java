package utc.edu.hongheo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.edu.hongheo.model.Category;
import utc.edu.hongheo.repository.ICategoryRepo;
import utc.edu.hongheo.service.ICategoryService;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepo categoryRepo;

    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepo.findById(id);
    }

    @Override
    public void delete(Long id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public Iterable<Category> findAllByStatus(int num, Long id) {
        return categoryRepo.findAllByStatus(num, id);
    }

    @Override
    public Iterable<Category> findAllByUserId(Long id) {
        return categoryRepo.findAllByUserId(id);
    }
}
