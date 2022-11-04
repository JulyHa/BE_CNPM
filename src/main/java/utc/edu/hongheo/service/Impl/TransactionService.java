package utc.edu.hongheo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.edu.hongheo.model.Transaction;
import utc.edu.hongheo.repository.ITransactionRepo;
import utc.edu.hongheo.service.ITransactionService;

import java.util.HashMap;
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

    public HashMap<Integer, Iterable<Transaction>> transactionsIncomeFor6Months(Long id, int status){
        HashMap<Integer, Iterable<Transaction>> transactionIE = new HashMap<>();
        String presentTime = String.valueOf(java.time.LocalDate.now());
        String[] time = presentTime.split("-");
        int firstYear = Integer.parseInt(time[0]);
        int firstMonth = Integer.parseInt(time[1]);
        int firstDay = Integer.parseInt(time[2]) - Integer.parseInt(time[2]) + 1;
        String currentMonth;
        if (firstMonth < 10) {
            currentMonth = firstYear + "-0" + firstMonth + "-0" + firstDay;
        } else {
            currentMonth = firstYear + "-" + firstMonth + "-0" + firstDay;
        }
        transactionIE.put(firstMonth, findAllTransactionsIEFor6Months(id, presentTime, currentMonth, status));


        firstDay = 31;
        for (int i = 1; i < 6; i++) {
            String timeNow;
            String nextTime;
            int day = 1;
            firstMonth = Integer.parseInt(time[1]) - i;
            if (firstMonth < 1) {
                firstMonth = 12;
                firstYear = firstYear - 1;
            }
            if (firstMonth < 10) {
                timeNow = firstYear + "-0" + firstMonth + "-" + firstDay;
                nextTime = firstYear + "-0" + firstMonth + "-0" + day;
            } else {
                timeNow = firstYear + "-" + firstMonth + "-" + firstDay;
                nextTime = firstYear + "-" + firstMonth + "-0" + day;
            }
            transactionIE.put(firstMonth, findAllTransactionsIEFor6Months(id, timeNow, nextTime, status));


        }
        return transactionIE;
    }
    @Override
    public Iterable<Transaction> findAllTransactionsIEFor6Months(Long id, String presentTime, String sixMonthsAgo, int status) {
        return iTransactionRepo.findAllTransactionsIEFor6Months(id, presentTime, sixMonthsAgo, status);
    }

//    @Override
//    public Iterable<Transaction> findAllTransactionsExpenseFor6Months(Long id, String presentTime, String sixMonthsAgo) {
//        return iTransactionRepo.findAllTransactionsIEFor6Months(id, presentTime, sixMonthsAgo);
//    }

    @Override
    public Iterable<Transaction> findAllByTransaction(String startTime, String endTime, int status, Long from, Long to, Long id) {
        return iTransactionRepo.findAllByTransaction(startTime, endTime, status, from, to, id);
    }
}
