package com.udea.lab1v2026;

import com.udea.lab1v2026.DTO.TransactionDTO;
import com.udea.lab1v2026.entity.Customer;
import com.udea.lab1v2026.entity.Transaction;
import com.udea.lab1v2026.repository.CustomerRepository;
import com.udea.lab1v2026.repository.TransactionRepository;
import com.udea.lab1v2026.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private TransactionService transactionService;

    private Customer sender;
    private Customer receiver;
    private TransactionDTO transactionDTO;

    @BeforeEach
    void setUp() {
        sender = new Customer(1L, "11111", "Sender", "User", 500.0);
        receiver = new Customer(2L, "22222", "Receiver", "User", 200.0);

        transactionDTO = new TransactionDTO();
        transactionDTO.setSenderAccountNumber("11111");
        transactionDTO.setReceiverAccountNumber("22222");
        transactionDTO.setAmount(100.0);
    }

    @Test
    void testTransferMoney_Success() {
        when(customerRepository.findByAccountNumber("11111")).thenReturn(Optional.of(sender));
        when(customerRepository.findByAccountNumber("22222")).thenReturn(Optional.of(receiver));

        Transaction savedTransaction = new Transaction(1L, "11111", "22222", 100.0, LocalDateTime.now());
        when(transactionRepository.save(any(Transaction.class))).thenReturn(savedTransaction);

        TransactionDTO result = transactionService.transferMoney(transactionDTO);

        assertNotNull(result);
        assertEquals(400.0, sender.getBalance());
        assertEquals(300.0, receiver.getBalance());
        assertEquals(100.0, result.getAmount());
    }

    @Test
    void testTransferMoney_InsufficientBalance() {
        transactionDTO.setAmount(1000.0);

        when(customerRepository.findByAccountNumber("11111")).thenReturn(Optional.of(sender));
        when(customerRepository.findByAccountNumber("22222")).thenReturn(Optional.of(receiver));

        assertThrows(IllegalArgumentException.class, () -> transactionService.transferMoney(transactionDTO));
    }

    @Test
    void testGetTransactionsForAccount() {
        Transaction tx = new Transaction(1L, "11111", "22222", 100.0, LocalDateTime.now());
        when(transactionRepository.findBySenderAccountNumberOrReceiverAccountNumber("11111", "11111"))
                .thenReturn(List.of(tx));

        List<TransactionDTO> result = transactionService.getTransactionsForAccount("11111");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("11111", result.get(0).getSenderAccountNumber());
    }
}