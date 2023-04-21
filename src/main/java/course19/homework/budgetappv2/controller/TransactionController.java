package course19.homework.budgetappv2.controller;

import course19.homework.budgetappv2.model.Transaction;
import course19.homework.budgetappv2.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transactions")
@Slf4j
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
        log.info("Transaction controller created");
    }

    @GetMapping
    public List<Transaction> getFilteredTransactions(@RequestParam(required = false) String type,
                                                     @RequestParam(required = false) Double minAmount,
                                                     @RequestParam(required = false) Double maxAmount
    ) {
        log.info("GET: Get filtered transactions call");
        return transactionService.getFilteredTransactions(type, minAmount, maxAmount);
    }

    @GetMapping("{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        log.info("GET: Get transaction by id call");
        return transactionService.getTransactionById(id);
    }

    @PostMapping
    public Transaction createNewTransaction(@RequestBody Transaction transaction) {
        log.info("POST: Create new transaction call");
        return transactionService.createNewTransaction(transaction);
    }

    @PutMapping("{id}")
    public Transaction replaceTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        log.info("PUT: Replace transaction with id call");
        return transactionService.replaceTransaction(id, transaction);
    }

    @PatchMapping("{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        log.info("PATCH: Update product or amount in transaction with id call");
        return transactionService.updateTransaction(id, transaction);
    }

    @DeleteMapping("{id}")
    public Transaction deleteTransactionById(@PathVariable Long id) {
        log.info("DELETE: Delete transaction by id call");
        return transactionService.deleteTransactionById(id);
    }
}
