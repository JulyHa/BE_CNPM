package utc.edu.hongheo.service;

import org.springframework.data.repository.query.Param;
import utc.edu.hongheo.model.Wallet;

public interface IWalletService extends IService<Wallet> {
    Iterable<Wallet> findAllByStatus( Long id);

    Iterable<Wallet> findAllByStatusPublicAndUserId(@Param("id") Long id);

    Iterable<Wallet> findAllByStatusPrivateAndUserId(@Param("id") Long id);
}
