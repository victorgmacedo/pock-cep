package br.com.cep.config;

import br.com.cep.dto.error.DefaultResponseError;
import br.com.cep.exception.CepException;
import br.com.cep.exception.CidadeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultResponseError> methodArgumentNotValidException(MethodArgumentNotValidException exception){
        Set<String> errors = exception.getFieldErrors().stream().map(ErrorHandler::getError).collect(Collectors.toSet());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultResponseError("Campos inválidos",errors));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<DefaultResponseError> methodArgumentNotValidException(ConstraintViolationException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultResponseError(exception.getMessage(), null));
    }

    @ExceptionHandler({CepException.class, CidadeException.class})
    public ResponseEntity<DefaultResponseError> defaultExeption(RuntimeException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultResponseError(exception.getMessage(), null));
    }

    private static String getError(FieldError fieldError){
        return fieldError.getDefaultMessage();
    }
}
