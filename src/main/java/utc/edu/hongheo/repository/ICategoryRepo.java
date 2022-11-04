package utc.edu.hongheo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utc.edu.hongheo.model.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepo extends JpaRepository<Category, Long> {
    Iterable<Category> findAllByUserId(Long id);

    @Modifying
    @Query("select e from Category e where e.status = ?1 and e.user.id = ?2")
    List<Category> findAllByStatus(int num, Long id);

    @Query("select e from Category e where e.color = ?1 and  e.user.id = ?2")
    Optional<Category> findByColor(String color, Long userId);
}
