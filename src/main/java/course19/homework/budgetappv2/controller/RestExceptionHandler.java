package course19.homework.budgetappv2.controller;

import course19.homework.budgetappv2.exception.TransactionNotFoundException;
import course19.homework.budgetappv2.exception.TransactionTypeNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(TransactionNotFoundException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleTransactionNotFoundException(TransactionNotFoundException transactionNotFoundException) {
        return new ErrorResponse(transactionNotFoundException.getMessage());
    }

    @ExceptionHandler(TransactionTypeNotValidException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleTransactionTypeNotValidException(TransactionTypeNotValidException transactionTypeNotValidException) {
        return new ErrorResponse(transactionTypeNotValidException.getMessage());
    }

    record ErrorResponse(String message) {
    }
}
