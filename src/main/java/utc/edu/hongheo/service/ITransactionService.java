package utc.edu.hongheo.service;

import utc.edu.hongheo.model.Transaction;

public interface ITransactionService extends IService<Transaction> {

    Iterable<Transaction> findAllCategoryById(Long id);

    Iterable<Transaction> findAllByWalletId(Long id);

    Iterable<Transaction> findAllByWallet(Long id);

    Iterable<Transaction> findAllByTimeMonthAndYear(int status, String month, int id);

    Iterable<Transaction>findAllTransactionsIEFor6Months(Long id, String presentTime, String sixMonthsAgo, int status);

//    Iterable<Transaction>findAllTransactionsExpenseFor6Months(Long id, String presentTime, String sixMonthsAgo);

    Iterable<Transaction>findAllByTransaction(String startTime,String endTime,int status,Long from,Long to,Long id);
}
