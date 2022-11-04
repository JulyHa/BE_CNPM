package utc.edu.hongheo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.edu.hongheo.model.Wallet;
import utc.edu.hongheo.repository.IWalletRepo;
import utc.edu.hongheo.service.IWalletService;

import java.util.Optional;

@Service
public class WalletService implements IWalletService {

    @Autowired
    private IWalletRepo iWalletRepo;

    @Override
    public Wallet save(Wallet wallet) {
        if (iWalletRepo.findWalletByName(wallet.getName(), wallet.getUser().getId()) != null)
            return null;
        return iWalletRepo.save(wallet);
    }

    @Override
    public Iterable<Wallet> findAll() {
        return iWalletRepo.findAll();
    }

    @Override
    public Optional<Wallet> findById(Long id) {
        return iWalletRepo.findById(id);
    }

    @Override
    public void delete(Long id) {
        iWalletRepo.deleteById(id);
    }

    @Override
    public boolean deleteWallet(Long id) {
        Optional<Wallet> optionalWallet = iWalletRepo.findById(id);
        if (!optionalWallet.isPresent()) {
            return false;
        }
        iWalletRepo.deleteById(id);
        return true;
    }

    @Override
    public Iterable<Wallet> findAllByStatus(Long id) {
        return iWalletRepo.findAllByStatus(id);
    }

    @Override
    public Iterable<Wallet> findAllByStatusPublicAndUserId(Long id) {
        return iWalletRepo.findAllByStatusPublicAndUserId(id);
    }

    @Override
    public Iterable<Wallet> findAllByStatusPrivateAndUserId(Long id) {
        return findAllByStatusPrivateAndUserId(id);
    }
}
