package utc.edu.hongheo.service;

import utc.edu.hongheo.model.Category;

public interface ICategoryService extends IService<Category>{

    Iterable<Category> findAllByStatus(int num, Long id);

    Iterable<Category> findAllByUserId(Long id);
}
