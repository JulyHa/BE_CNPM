package utc.edu.hongheo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import utc.edu.hongheo.model.Transaction;

@Repository
public interface ITransactionRepo extends JpaRepository<Transaction, Long> {
    @Query("select category from Category category where category.id = ?1")
    Iterable<Transaction> findAllByCategoryId(Long id);

    @Query("select t from Transaction t where t.wallet.id = ?1 order by t.id DESC")
    Iterable<Transaction> findAllByWalletId(Long id);

    @Query("select t from Transaction t where t.time between ?1 and ?2")
    Iterable<Transaction> findAllByTimeBetween(@Param("startTime") String startTime, @Param("endTime") String endTime);


    @Query("select c.name, t.id, t.note, t.time, t.totalSpent,t.category.id, t.wallet.id, w.name, u.username\n" +
            "from Transaction t join Category c on t.category.id = c.id\n" +
            "join Wallet w on t.wallet.id = w.id\n" +
            "join User u on  w.user.id = u.id\n" +
            "where u.id = ?1")
    Iterable<Transaction> findAllByUserId(@PathVariable Long id);

    @Query("select c.name, t.id, t.note, t.time, t.totalSpent,t.category.id, t.wallet.id, w.name, u.username\n " +
            "from Transaction t join Category c on t.category.id = c.id\n" +
            "join Wallet w on t.wallet.id = w.id\n" +
            "join User u on  w.user.id = u.id\n" +
            "where u.id = ?1 and t.category.id = 1")
    Iterable<Transaction> findAllByCategoryIncomeUserId(@PathVariable Long id);

    @Query("select c.name, t.id, t.note, t.time, t.totalSpent,t.category.id, t.wallet.id, w.name, u.username\n" +
            "from Transaction t\n" +
            "join Category c on t.category.id = c.id\n" +
            "join Wallet w on t.wallet.id = w.id\n" +
            "join User u on  w.user.id = u.id\n" +
            "where u.id = ?1 and t.category.id = 2")
    Iterable<Transaction> findAllByCategoryExpenseUserId(@PathVariable Long id);

    @Query(value = "select * from transaction where wallet_id=? order by id DESC", nativeQuery = true)
    Iterable<Transaction> findAllByWallet(@PathVariable Long id);

//    @Query("select t.id, t.note, t.time, t.totalSpent, t.wallet.id, t.category.id \n" +
//            "from Transaction t join Category c on c.id = t.category.id \n" +
//            "where c.status = ?1 \n" +
//            "and t.time like concat('%',?2, '%') and t.wallet.id  = ?3")
        @Query(value = "select t.id, t.note, t.time, t.total_spent, t.wallet_id, t.category_id\n" +
        "from transaction t\n" +
        "         join category c on c.id = t.category_id\n" +
        "where c.status = :status\n" +
        " and t.time like concat('%',:month, '%') and t.wallet_id  = :id", nativeQuery = true)
    Iterable<Transaction> findAllByTimeMonthAndYear(@Param("status") int status, @Param("month") String month,@Param("id") int id);

    @Query(value = "select * from transaction t\n" +
            "join wallet w on t.wallet_id = w.id\n" +
            "join category c on c.id = t.category_id\n" +
            "where t.time >= :sixMonthsAgo and time<= :presentTime and w.id = :id and c.status = :status",nativeQuery = true)
    Iterable<Transaction>findAllTransactionsIEFor6Months(@PathVariable Long id, @Param("presentTime") String presentTime, @Param("sixMonthsAgo") String sixMonthsAgo, @Param("status") int status);
//    @Query(value = "select * from transaction t\n" +
//            "join wallet w on t.wallet_id = w.id\n" +
//            "join category c on c.id = t.category_id\n" +
//            "where t.time >= :sixMonthsAgo and time<= :presentTime and w.id = :id and c.status = 2",nativeQuery = true)
//    Iterable<Transaction>findAllTransactionsExpenseFor6Months(@PathVariable Long id, @Param("presentTime") String presentTime, @Param("sixMonthsAgo") String sixMonthsAgo);

    @Query(value = "select transaction.id, transaction.note,transaction.total_spent,category_id,wallet_id,transaction.time\n" +
            "from transaction join category c on c.id = transaction.category_id\n" +
            "where time >= :startTime and time<= :endTime and c.status= :status and total_spent between :from and :to and wallet_id = :id",nativeQuery = true)
    Iterable<Transaction>findAllByTransaction(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("status")int status,@Param("from")Long from,@Param("to")Long to,@Param("id")Long id);

}
