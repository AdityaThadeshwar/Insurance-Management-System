package com.aditya.insurance.management.system.exceptions.customized;

import com.aditya.insurance.management.system.exceptions.AddressNotFoundException;
import com.aditya.insurance.management.system.exceptions.ClaimNotFoundException;
import com.aditya.insurance.management.system.exceptions.CustomerNotFoundException;
import com.aditya.insurance.management.system.exceptions.PolicyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    //Define exception type to handle
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleAddressNotFoundException(Exception ex, WebRequest request) throws Exception {
        String message = "Address is not present for the customer";
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), message, request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleCustomerNotFoundException(Exception ex, WebRequest request) throws Exception {
        String message = "Customer not found";
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), message, request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(PolicyNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handlePolicyNotFoundException(Exception ex, WebRequest request) throws Exception {
        String message = "Policy not found";
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), message, request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ClaimNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleClaimNotFoundException(Exception ex, WebRequest request) throws Exception {
        String message = "Claim not found";
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), message, request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);

    }
}
