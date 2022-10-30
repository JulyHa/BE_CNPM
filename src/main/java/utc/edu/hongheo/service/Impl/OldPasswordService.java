package utc.edu.hongheo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.edu.hongheo.model.OldPassword;
import utc.edu.hongheo.repository.IOldPasswordRepo;
import utc.edu.hongheo.service.IOldPasswordService;

import java.util.Optional;

@Service
public class OldPasswordService implements IOldPasswordService {
    @Autowired
    private IOldPasswordRepo iOldPasswordRepo;

    @Override
    public Iterable<OldPassword> findAllByUserIdTop3OldPassword(Long id) {
        return iOldPasswordRepo.findAllByUserIdTop3OldPassword(id);
    }

    @Override
    public OldPassword save(OldPassword oldPassword) {
        return iOldPasswordRepo.save(oldPassword);
    }

    @Override
    public Iterable<OldPassword> findAll() {
        return iOldPasswordRepo.findAll();
    }

    @Override
    public Optional<OldPassword> findById(Long id) {
        return iOldPasswordRepo.findById(id);
    }

    @Override
    public void delete(Long id) {
        iOldPasswordRepo.deleteById(id);
    }
}
