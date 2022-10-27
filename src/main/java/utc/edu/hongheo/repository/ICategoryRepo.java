package utc.edu.hongheo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import utc.edu.hongheo.model.Category;

import java.util.List;

@Repository
public interface ICategoryRepo extends JpaRepository<Category, Long> {
    Iterable<Category> findAllByUserId(Long id);

    @Modifying
    @Query(value = "select * from category where status = :num and user_id = :id", nativeQuery = true)
    List<Category> findAllByStatus(int num, Long id);
}
