package utc.edu.hongheo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.edu.hongheo.model.Transaction;
import utc.edu.hongheo.repository.ITransactionRepo;
import utc.edu.hongheo.service.ITransactionService;

import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {
    @Autowired
    private ITransactionRepo iTransactionRepo;

    @Override
    public Transaction save(Transaction transaction) {
        return iTransactionRepo.save(transaction);
    }

    @Override
    public Iterable<Transaction> findAll() {
        return iTransactionRepo.findAll();
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return iTransactionRepo.findById(id);
    }

    @Override
    public void delete(Long id) {
        iTransactionRepo.deleteById(id);
    }

    @Override
    public Iterable<Transaction> findAllCategoryById(Long id) {
        return iTransactionRepo.findAllByCategoryId(id);
    }

    @Override
    public Iterable<Transaction> findAllByWalletId(Long id) {
        return iTransactionRepo.findAllByWalletId(id);
    }

    @Override
    public Iterable<Transaction> findAllByWallet(Long id) {
        return iTransactionRepo.findAllByWallet(id);
    }

    @Override
    public Iterable<Transaction> findAllByTimeMonthAndYear(int status, String month, int id) {
        return iTransactionRepo.findAllByTimeMonthAndYear(status, month, id);
    }

    @Override
    public Iterable<Transaction> findAllTransactionsIncomeFor6Months(Long id, String presentTime, String sixMonthsAgo) {
        return iTransactionRepo.findAllTransactionsIncomeFor6Months(id, presentTime, sixMonthsAgo);
    }

    @Override
    public Iterable<Transaction> findAllTransactionsExpenseFor6Months(Long id, String presentTime, String sixMonthsAgo) {
        return iTransactionRepo.findAllTransactionsExpenseFor6Months(id, presentTime, sixMonthsAgo);
    }

    @Override
    public Iterable<Transaction> findAllByTransaction(String startTime, String endTime, int status, Long from, Long to, Long id) {
        return iTransactionRepo.findAllByTransaction(startTime, endTime, status, from, to, id);
    }
}
