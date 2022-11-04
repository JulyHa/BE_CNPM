package utc.edu.hongheo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import utc.edu.hongheo.model.Wallet;

@Repository
public interface IWalletRepo extends JpaRepository<Wallet, Long> {
    @Modifying
    @Query("select w from Wallet w where w.status = 1 and w.user.id = ?2")
    Iterable<Wallet> findAllByStatusPublicAndUserId(@Param("id") Long id);

    @Modifying
    @Query("select w from Wallet w where w.status = 0 and w.user.id = ?2")
    Iterable<Wallet> findAllByStatusPrivateAndUserId(@PathVariable Long id);

    @Modifying
    @Query("select w from Wallet w where w.user.id = ?1 and (w.status = 1 or w.status = 2)")
    Iterable<Wallet> findAllByStatus(@PathVariable Long id);

    @Query("select w from Wallet w where w.name = ?1 and w.user.id = ?2")
    Wallet findWalletByName(@PathVariable String name, @Param("id") Long id);
}
