package utc.edu.hongheo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utc.edu.hongheo.model.Category;
import utc.edu.hongheo.model.Transaction;
import utc.edu.hongheo.model.Wallet;
import utc.edu.hongheo.service.ICategoryService;
import utc.edu.hongheo.service.IWalletService;
import utc.edu.hongheo.service.Impl.TransactionService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private IWalletService walletService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<Iterable<Transaction>> findAll(){
        Iterable<Transaction> list = transactionService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Long id){
        Optional<Transaction> transaction = transactionService.findById(id);
        return transaction.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Transaction> save(@RequestBody Transaction transaction){
        return new ResponseEntity<>(transactionService.save(transaction), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Transaction> update(@PathVariable Long id, @RequestBody Transaction transaction){
        Optional<Transaction> editTransaction = transactionService.findById(id);
        if(!editTransaction.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Wallet> wallet = walletService.findById(editTransaction.get().getWallet().getId());
        if(!wallet.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Category> category = categoryService.findById(transaction.getCategory().getId());
        if(!category.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        transaction.setId(id);
        transaction.setCategory(category.get());
        int oldTransaction = editTransaction.get().getCategory().getStatus();
        int newTransaction = transaction.getCategory().getStatus();
        if ((oldTransaction == 1) && (newTransaction == 1)) {
            wallet.get().setMoneyAmount(wallet.get().getMoneyAmount() - editTransaction.get().getTotalSpent() + transaction.getTotalSpent() );
        } else if ((oldTransaction == 1) && (newTransaction == 2)) {
            wallet.get().setMoneyAmount(wallet.get().getMoneyAmount() - editTransaction.get().getTotalSpent() - transaction.getTotalSpent());
        } else if ((oldTransaction == 2) && (newTransaction == 1)) {
            wallet.get().setMoneyAmount(wallet.get().getMoneyAmount() + editTransaction.get().getTotalSpent() + transaction.getTotalSpent());
        } else {
            wallet.get().setMoneyAmount(wallet.get().getMoneyAmount() + editTransaction.get().getTotalSpent() - transaction.getTotalSpent());
        }
        transactionService.save(transaction);
        walletService.save(wallet.get());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/create")
    public ResponseEntity<Optional<Transaction>> createTransaction(@RequestBody Transaction transaction){
        Optional<Wallet> wallet = walletService.findById(transaction.getWallet().getId());
        if(!wallet.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Category> category = categoryService.findById(transaction.getCategory().getId());
        if(!category.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        transaction.getCategory().setStatus(category.get().getStatus());
        transactionService.save(transaction);
        if (transaction.getCategory().getStatus() == 1) {
            wallet.get().setMoneyAmount(wallet.get().getMoneyAmount() + transaction.getTotalSpent());
            walletService.save(wallet.get());
        } else {
            wallet.get().setMoneyAmount(wallet.get().getMoneyAmount() - transaction.getTotalSpent());
            walletService.save(wallet.get());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("find-by-wallet/{id}")
    public ResponseEntity<Iterable<Transaction>> findAll(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.findAllByWalletId(id), HttpStatus.OK);
    }

    @GetMapping("find-by-category/{id}")
    public ResponseEntity<Iterable<Transaction>> findAllByCategory_Id(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.findAllCategoryById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> removeTransaction(@PathVariable Long id) {
        Optional<Transaction> transactionDel = transactionService.findById(id);
        if(!transactionDel.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Wallet> editWallet = walletService.findById(transactionDel.get().getWallet().getId());
        if(!editWallet.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        editWallet.get().setId(transactionDel.get().getWallet().getId());
        if (transactionDel.get().getCategory().getStatus() == 1) {
            editWallet.get().setMoneyAmount(editWallet.get().getMoneyAmount() - transactionDel.get().getTotalSpent());
        } else {
            editWallet.get().setMoneyAmount(editWallet.get().getMoneyAmount() + transactionDel.get().getTotalSpent());
        }
        walletService.save(editWallet.get());
        transactionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("find-all-by-time")
    public ResponseEntity<Iterable<Transaction>> findAllByMonthTimeAndYearTime(@RequestParam("status") int status, @RequestParam("id") int id) {
        String month = String.valueOf(YearMonth.now());
        return new ResponseEntity<>(transactionService.findAllByTimeMonthAndYear(status, month, id), HttpStatus.OK);
    }

    @GetMapping("/find-all-income-6month/{id}")
    public ResponseEntity<HashMap<Integer, Iterable<Transaction>>> findAllTransactionsIncomeFor6Months(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.transactionsIncomeFor6Months(id, 1), HttpStatus.OK);
    }

    @GetMapping("/find-all-expense-6month/{id}")
    public ResponseEntity<HashMap<Integer, Iterable<Transaction>>> findAllTransactionsExpenseFor6Months(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.transactionsIncomeFor6Months(id, 2), HttpStatus.OK);
    }

    @GetMapping("find-all-transaction")
    public ResponseEntity<Iterable<Transaction>> findAllTransactions(@RequestParam String startTime, @RequestParam String endTime, @RequestParam int status, @RequestParam Long from, @RequestParam Long to, @RequestParam Long id) {
        if (startTime.equals("")) {
            startTime = "1900-01-01";
        }
        if (endTime.equals("")) {
            endTime = "3000-01-01";
        }
        return new ResponseEntity<>(transactionService.findAllByTransaction(String.valueOf(LocalDate.parse(startTime)), String.valueOf(LocalDate.parse(endTime)), status, from, to, id), HttpStatus.OK);
    }

}
