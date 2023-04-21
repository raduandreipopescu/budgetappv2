package course19.homework.budgetappv2.controller;

import course19.homework.budgetappv2.model.Transaction;
import course19.homework.budgetappv2.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("transactions/reports")
@Slf4j
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
        log.info("Report controller created");
    }

    @GetMapping("type")
    public Map<String, List<Transaction>> getTransactionsByType() {
        log.info("GET: Get report transactions by type call");
        return reportService.getTransactionsByType();
    }

    @GetMapping("product")
    public Map<String, List<Transaction>> getTransactionsByProduct() {
        log.info("GET: Get report transactions by product call");
        return reportService.getTransactionsByProduct();
    }
}
