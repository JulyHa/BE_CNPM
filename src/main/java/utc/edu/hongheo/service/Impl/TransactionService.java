package utc.edu.hongheo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.edu.hongheo.model.Transaction;
import utc.edu.hongheo.model.Wallet;
import utc.edu.hongheo.repository.ITransactionRepo;
import utc.edu.hongheo.repository.IWalletRepo;
import utc.edu.hongheo.service.ITransactionService;

import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {
    @Autowired
    private ITransactionRepo iTransactionRepo;
    @Autowired
    private IWalletRepo iWalletRepo;

    @Override
    public Transaction save(Transaction transaction) {
        if (transaction.getTotalSpent() > 0 && !transaction.getTime().isEmpty()
                && transaction.getCategory().getId() > 0) {
            Optional<Wallet> wallet = iWalletRepo.findById(transaction.getWallet().getId());
            int newTransaction = transaction.getCategory().getStatus();
            wallet.get().setId(wallet.get().getId());
            if (newTransaction == 1) {
                wallet.get().setMoneyAmount(wallet.get().getMoneyAmount() + transaction.getTotalSpent());
            } else if (newTransaction == 2) {
                wallet.get().setMoneyAmount(wallet.get().getMoneyAmount() - transaction.getTotalSpent());
            }
            return iTransactionRepo.save(transaction);
        }
        return null;
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
