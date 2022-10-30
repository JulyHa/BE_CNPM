package utc.edu.hongheo.service;

import utc.edu.hongheo.model.Role;

public interface IRoleService extends IService<Role> {
    Role findByName(String name);
}
