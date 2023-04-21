package course19.homework.budgetappv2.repository;

import course19.homework.budgetappv2.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTransactionByType(String type);

    List<Transaction> findTransactionByAmountGreaterThan(Double minAmount);

    List<Transaction> findTransactionByAmountLessThan(Double maxAmount);

    List<Transaction> findTransactionByTypeAndAmountGreaterThan(String type, Double minAmount);

    List<Transaction> findTransactionByTypeAndAmountLessThan(String type, Double maxAmount);

    List<Transaction> findTransactionByAmountBetween(Double minAmount, Double maxAmount);

    List<Transaction> findTransactionByTypeAndAmountBetween(String type, Double minAmount, Double maxAmount);
}
