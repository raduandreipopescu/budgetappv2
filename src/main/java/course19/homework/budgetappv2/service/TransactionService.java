package course19.homework.budgetappv2.service;

import course19.homework.budgetappv2.exception.TransactionNotFoundException;
import course19.homework.budgetappv2.exception.TransactionTypeNotValidException;
import course19.homework.budgetappv2.model.Transaction;
import course19.homework.budgetappv2.model.TransactionType;
import course19.homework.budgetappv2.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
        log.info("Transaction service created");
    }

    public List<Transaction> getFilteredTransactions(String type, Double minAmount, Double maxAmount) {
        if (type != null && minAmount == null && maxAmount == null) {
            checkTransactionType(type);
            return repository.findTransactionByType(type);
        }
        if (type == null && minAmount != null && maxAmount == null) {
            return repository.findTransactionByAmountGreaterThan(minAmount);
        }
        if (type == null && minAmount == null && maxAmount != null) {
            return repository.findTransactionByAmountLessThan(maxAmount);
        }
        if (type != null && minAmount != null && maxAmount == null) {
            checkTransactionType(type);
            return repository.findTransactionByTypeAndAmountGreaterThan(type, minAmount);
        }
        if (type != null && minAmount == null && maxAmount != null) {
            checkTransactionType(type);
            return repository.findTransactionByTypeAndAmountLessThan(type, maxAmount);
        }
        if (type == null && minAmount != null && maxAmount != null) {
            return repository.findTransactionByAmountBetween(minAmount, maxAmount);
        }
        if (type != null && minAmount != null && maxAmount != null) {
            checkTransactionType(type);
            return repository.findTransactionByTypeAndAmountBetween(type, minAmount, maxAmount);
        }
        return repository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction with id %d was not found!".formatted(id)));
    }

    public Transaction createNewTransaction(Transaction transaction) {
        checkTransactionType(transaction.getType());
        return repository.save(transaction);
    }

    public Transaction replaceTransaction(Long id, Transaction transaction) {
        getTransactionById(id);
        checkTransactionType(transaction.getType());
        return repository.save(transaction.withId(id));
    }

    public Transaction updateTransaction(Long id, Transaction transaction) {
        Transaction updatedTransaction = getTransactionById(id);
        if (transaction.getProduct() != null) {
            updatedTransaction.setProduct(transaction.getProduct());
        }
        if (transaction.getAmount() != null) {
            updatedTransaction.setAmount(transaction.getAmount());
        }
        return repository.save(updatedTransaction);
    }

    public Transaction deleteTransactionById(Long id) {
        Transaction deletedTransaction = getTransactionById(id);
        repository.deleteById(id);
        return deletedTransaction;
    }

    private static void checkTransactionType(String inputType) {
        Arrays.stream(TransactionType.values())
                .filter(type -> type.name().equals(inputType))
                .findFirst()
                .orElseThrow(() -> new TransactionTypeNotValidException(
                        "Transaction type %s is not valid. Only BUY or SELL allowed.".formatted(inputType)));
    }
}
