package course19.homework.budgetappv2.exception;

public class TransactionTypeNotValidException extends RuntimeException {
    public TransactionTypeNotValidException(String message) {
        super(message);
    }
}
