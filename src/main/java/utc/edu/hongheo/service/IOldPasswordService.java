package utc.edu.hongheo.service;

import utc.edu.hongheo.model.OldPassword;

public interface IOldPasswordService extends IService<OldPassword>{
    Iterable<OldPassword> findAllByUserIdTop3OldPassword(Long id);
}
