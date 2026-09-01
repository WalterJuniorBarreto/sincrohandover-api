package com.sincrohandover.api.shared.infrastructure.web;

import com.sincrohandover.api.shared.domain.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.sqm.DynamicInstantiationNature;
import org.jspecify.annotations.Nullable;
import org.slf4j.MDC;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.boot.micrometer.observation.autoconfigure.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Interceptor global de excepciones (Capa de Infraestructura Web).
 * Estandariza las respuestas de error utilizando RFC 7807 (Problem Details).
 * Aplica principios de Ciberseguridad evitando la fuga de Stack Traces.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler{

    /**
     * Maneja errores de validación de Jakarta (@Valid en los Records/DTOs).
     * Retorna 400 Bad Request con el detalle de qué campos fallaron.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Error de validacion en los datos de entrado");
        problemDetail.setType(URI.create("http://api.sincrohandover.com/errors/validation-failed"));
        problemDetail.setTitle("Bad Request");
        problemDetail.setProperty("timestamp", Instant.now());

        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()){
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        problemDetail.setProperty("invalid_params", errors);

        log.warn("Fallo de validacion de payload: {}", errors);
        return problemDetail;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setType(URI.create("https://api.sincrohandover.com/errors/resource-not-found"));
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setProperty("timestamp", Instant.now());

        log.warn("Recurso no encontrado: {}", ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleAllUncaughtExceptions(Exception ex) {
        String correlationId = MDC.get("correlation_id");
        log.error("Error interno no controlado, COrrelation-ID: {}", correlationId, ex);

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Ha ocurrido un error inesperado en el servidor. Contacte a soporte técnico.");
        problemDetail.setType(URI.create("https://api.sincrohandover.com/errors/internal-server-error"));
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setProperty("timestamp", Instant.now());

        problemDetail.setProperty("trace_id", correlationId);

        return problemDetail;
    }

}



