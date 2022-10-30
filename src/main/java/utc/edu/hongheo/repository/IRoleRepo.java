package utc.edu.hongheo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utc.edu.hongheo.model.Role;

@Repository
public interface IRoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String roleName);

}
