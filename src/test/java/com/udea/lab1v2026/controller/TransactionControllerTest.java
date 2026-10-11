package com.udea.lab1v2026.controller;

import com.udea.lab1v2026.DTO.TransactionDTO;
import com.udea.lab1v2026.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    private TransactionDTO transactionDTO;

    @BeforeEach
    void setUp() {
        transactionDTO = new TransactionDTO();
        transactionDTO.setId(1L);
        transactionDTO.setSenderAccountNumber("123456");
        transactionDTO.setReceiverAccountNumber("654321");
        transactionDTO.setAmount(200.0);
    }

    @Test
    void testTransferMoney() {
        when(transactionService.transferMoney(any(TransactionDTO.class))).thenReturn(transactionDTO);

        ResponseEntity<TransactionDTO> response = transactionController.transferMoney(transactionDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(200.0, response.getBody().getAmount());
    }

    @Test
    void testGetTransactionsByAccount() {
        when(transactionService.getTransactionsForAccount("123456")).thenReturn(List.of(transactionDTO));

        ResponseEntity<List<TransactionDTO>> response = transactionController.getTransactionsByAccount("123456");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("123456", response.getBody().get(0).getSenderAccountNumber());
    }
}