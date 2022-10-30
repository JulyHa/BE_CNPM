package utc.edu.hongheo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.edu.hongheo.model.Role;
import utc.edu.hongheo.repository.IRoleRepo;
import utc.edu.hongheo.service.IRoleService;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepo iRoleRepo;
    @Override
    public Role findByName(String name) {
        return iRoleRepo.findByName(name);
    }

    @Override
    public Role save(Role role) {
        return iRoleRepo.save(role);
    }

    @Override
    public Iterable<Role> findAll() {
        return iRoleRepo.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return iRoleRepo.findById(id);
    }

    @Override
    public void delete(Long id) {
        iRoleRepo.deleteById(id);
    }
}
