package course19.homework.budgetappv2.service;

import course19.homework.budgetappv2.model.Transaction;
import course19.homework.budgetappv2.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReportService {
    private final TransactionRepository repository;

    public ReportService(TransactionRepository repository) {
        this.repository = repository;
        log.info("Report service created");
    }

    public Map<String, List<Transaction>> getTransactionsByType() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(Transaction::getType));
    }

    public Map<String, List<Transaction>> getTransactionsByProduct() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(Transaction::getProduct));
    }
}
