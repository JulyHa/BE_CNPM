package utc.edu.hongheo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utc.edu.hongheo.model.User;

@Repository
public interface IUserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

    boolean existsByUsername(String username);


}
