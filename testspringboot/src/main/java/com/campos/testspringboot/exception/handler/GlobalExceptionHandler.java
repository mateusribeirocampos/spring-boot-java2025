package com.campos.testspringboot.exception.handler;

import com.campos.testspringboot.exception.ExceptionResponse;
import com.campos.testspringboot.exception.RequiredObjectIsNullException;
import com.campos.testspringboot.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

// Classe que trata exceções globais para toda a aplicação
@ControllerAdvice
public class GlobalExceptionHandler {

    // Logger para registrar logs de erro, aviso e informação
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Trata qualquer exceção genérica não capturada por outros handlers
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request) {
        // Loga o erro interno do servidor
        logger.error("Internal server error: ", ex);

        // Cria uma resposta personalizada para exceções genéricas
        ExceptionResponse response = ExceptionResponse.of("Internal Server error",
                ex.getMessage(),
                request.getDescription(false));

        // Retorna resposta HTTP 500 (Internal Server Error)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    // Trata exceções do tipo ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        // Loga um aviso de recurso não encontrado
        logger.warn("Resource not found: {} ", ex.getMessage());

        // Cria uma resposta personalizada para recurso não encontrado
        ExceptionResponse response = ExceptionResponse.of("Resource Not Found",
                ex.getMessage(),
                request.getDescription(false));

        // Retorna resposta HTTP 404 (Not Found)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Trata exceções do tipo RequiredObjectIsNullException
    @ExceptionHandler(RequiredObjectIsNullException.class)
    public ResponseEntity<ExceptionResponse> handleRequiredObjectIsNullException(RequiredObjectIsNullException ex, WebRequest request) {
        // Loga um aviso de objeto obrigatório nulo
        logger.warn("Required object is null : {} ", ex.getMessage());

        // Cria uma resposta personalizada para requisição inválida
        ExceptionResponse response = ExceptionResponse.of("Bad request",
                ex.getMessage(),
                request.getDescription(false));

        // Retorna resposta HTTP 400 (Bad Request)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Trata exceções de validação de argumentos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Loga um aviso de falha de validação
        logger.warn("Validation failed : {} ", ex.getMessage());

        // Cria um mapa para armazenar os erros de validação campo a campo
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            // Obtém o nome do campo com erro
            String fieldName = ((FieldError) error).getField();
            // Obtém a mensagem de erro
            String errorMessage = error.getDefaultMessage();
            // Adiciona ao mapa de erros
            errors.put(fieldName, errorMessage);
        });
        // Retorna resposta HTTP 400 com os erros de validação
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
