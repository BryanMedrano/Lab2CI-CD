package com.udea.lab1v2026.exception;

import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void testHandleIllegalArgument() {
        IllegalArgumentException ex = new IllegalArgumentException("Saldo insuficiente en la cuenta.");

        ResponseEntity<Map<String, Object>> response = handler.handleIllegalArgument(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Solicitud Inválida", response.getBody().get("error"));
        assertEquals("Saldo insuficiente en la cuenta.", response.getBody().get("message"));
        assertEquals(400, response.getBody().get("status"));
        assertNotNull(response.getBody().get("timestamp"));
    }

    @Test
    void testHandleValidationExceptions() throws NoSuchMethodException {
        // Crear un BindingResult simulando errores de validación de Spring (@Valid)
        BindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "targetObject");
        bindingResult.addError(new FieldError("targetObject", "balance", "El saldo debe ser positivo"));

        // Obtener un MethodParameter ficticio para instanciar MethodArgumentNotValidException
        MethodParameter parameter = new MethodParameter(
                this.getClass().getDeclaredMethod("testHandleValidationExceptions"), -1
        );

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(parameter, bindingResult);

        ResponseEntity<Map<String, Object>> response = handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Error de Validación", response.getBody().get("error"));
        assertEquals(400, response.getBody().get("status"));

        @SuppressWarnings("unchecked")
        Map<String, String> details = (Map<String, String>) response.getBody().get("details");
        assertNotNull(details);
        assertEquals("El saldo debe ser positivo", details.get("balance"));
    }
}