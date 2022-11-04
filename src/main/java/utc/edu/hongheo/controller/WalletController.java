package utc.edu.hongheo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utc.edu.hongheo.model.Transaction;
import utc.edu.hongheo.model.Wallet;
import utc.edu.hongheo.service.ITransactionService;
import utc.edu.hongheo.service.IWalletService;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("wallets")
public class WalletController {
    @Autowired
    private IWalletService walletService;

    @Autowired
    private ITransactionService transactionService;

    @GetMapping("find-by-user/{id}")
    public ResponseEntity<Iterable<Wallet>> findAll(@PathVariable Long id) {
        return new ResponseEntity<>(walletService.findAllByStatus(id), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Wallet> findById(@PathVariable Long id){
        Optional<Wallet> wallet = walletService.findById(id);
        if(!wallet.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wallet.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Wallet> save(@RequestBody Wallet wallet){
        return new ResponseEntity<>(walletService.save(wallet), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Wallet> update(@PathVariable Long id, @RequestBody Wallet wallet){
        Optional<Wallet> optionalWallet = walletService.findById(id);
        if(!optionalWallet.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        wallet.setId(id);
        return new ResponseEntity<>(walletService.save(wallet), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){

        return new ResponseEntity<>(walletService.deleteWallet(id), HttpStatus.OK);
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<Iterable<Wallet>> findAllByStatusPrivateAndUserId(@PathVariable Long id){
        return new ResponseEntity<>(walletService.findAllByStatusPrivateAndUserId(id), HttpStatus.OK);
    }

    @GetMapping("/find-by-ownerId")
    public ResponseEntity<Iterable<Wallet>> findAllByStatusPublicAndUserId(@RequestParam Long id) {
        return new ResponseEntity<>(walletService.findAllByStatusPublicAndUserId(id), HttpStatus.OK);
    }

    @PutMapping("/edit-money-type/{id}")
    public ResponseEntity<Wallet> updateWallet(@PathVariable Long id, @RequestBody Wallet wallet){
        Optional<Wallet> optionalWallet =walletService.findById(id);
        if(!optionalWallet.isPresent()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        if(optionalWallet.get().getMoneyType().getId().equals(wallet.getMoneyType().getId())){
            wallet.setId(id);
            return new ResponseEntity<>(walletService.save(wallet), HttpStatus.OK);
        }
        else {
            wallet.setId(id);
            Iterable<Transaction> transactions = transactionService.findAllByWallet(id);
            if(optionalWallet.get().getMoneyType().getId() == 1){
                wallet.setMoneyAmount(Math.ceil((optionalWallet.get().getMoneyAmount() / 23000) * 100) / 100);
            }
            else {
                wallet.setMoneyAmount(Math.ceil((optionalWallet.get().getMoneyAmount() * 23000) * 100) / 100);
            }
            for (Transaction transaction : transactions){
                transaction.setId(transaction.getId());
                if(optionalWallet.get().getMoneyType().getId() == 1){
                    transaction.setTotalSpent(Math.ceil((transaction.getTotalSpent() / 23000) * 100) / 100);
                }
                else {
                    transaction.setTotalSpent(Math.ceil((transaction.getTotalSpent() * 23000) * 100) / 100);
                }
                transactionService.save(transaction);
            }
            wallet.setIcon(optionalWallet.get().getIcon());
            wallet.setName(optionalWallet.get().getName());
            wallet.setUser(optionalWallet.get().getUser());
            wallet.setStatus(optionalWallet.get().getStatus());
            return new ResponseEntity<>(walletService.save(wallet), HttpStatus.OK);
        }
    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity<Wallet> updateStatus(@PathVariable Long id, @RequestBody Wallet wallet) {
        Optional<Wallet> walletOptional = walletService.findById(id);
        if (!walletOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        wallet.setId(id);
        if (wallet.getStatus() == 1) {
            wallet.setStatus(2);
        }
        if (wallet.getStatus() == 2) {
            wallet.setStatus(1);
        }
        return new ResponseEntity<>(walletService.save(wallet), HttpStatus.OK);
    }
}
