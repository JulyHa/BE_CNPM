package utc.edu.hongheo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import utc.edu.hongheo.model.OldPassword;

@Repository
public interface IOldPasswordRepo extends JpaRepository<OldPassword, Long> {
    @Query(value = "select e from OldPassword e where e.user.id = ?1 order by e.id DESC limit 3",  nativeQuery = true)
    Iterable<OldPassword> findAllByUserIdTop3OldPassword(@PathVariable Long id);
}
