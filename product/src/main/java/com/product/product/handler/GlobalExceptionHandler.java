package com.product.product.handler;


import com.product.product.exception.PurchaseProductException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PurchaseProductException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(PurchaseProductException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        var erros = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors().forEach((err) -> {
            var filedName = ((FieldError) err).getField();
            var errorMessage = err.getDefaultMessage();
            erros.put(filedName, errorMessage);

        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(erros));
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException exp) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exp.getMessage());
    }

}

