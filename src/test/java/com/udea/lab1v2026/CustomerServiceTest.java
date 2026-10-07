package com.udea.lab1v2026;

import com.udea.lab1v2026.DTO.CustomerDTO;
import com.udea.lab1v2026.entity.Customer;
import com.udea.lab1v2026.mapper.CustomerMapper;
import com.udea.lab1v2026.repository.CustomerRepository;
import com.udea.lab1v2026.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;
    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        customer = new Customer(1L, "12345", "Juan", "Perez", 1000.0);
        customerDTO = new CustomerDTO(1L, "Juan", "Perez", "12345", 1000.0);
    }

    @Test
    void testGetAllCustomers() {
        when(customerRepository.findAll()).thenReturn(List.of(customer));
        when(customerMapper.toDTO(any(Customer.class))).thenReturn(customerDTO);

        List<CustomerDTO> result = customerService.getAllCustomers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("12345", result.get(0).getAccountNumber());
    }

    @Test
    void testGetCustomerById_Success() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        CustomerDTO result = customerService.getCustomerById(1L);

        assertNotNull(result);
        assertEquals("Juan", result.getFirstName());
    }

    @Test
    void testGetCustomerById_NotFound() {
        when(customerRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> customerService.getCustomerById(99L));
    }

    @Test
    void testCreateCustomer() {
        when(customerMapper.toEntity(customerDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        CustomerDTO result = customerService.createCustomer(customerDTO);

        assertNotNull(result);
        assertEquals("12345", result.getAccountNumber());
    }

    @Test
    void testDeleteCustomer_Success() {
        when(customerRepository.existsById(1L)).thenReturn(true);
        doNothing().when(customerRepository).deleteById(1L);

        assertDoesNotThrow(() -> customerService.deleteCustomer(1L));
        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteCustomer_NotFound() {
        when(customerRepository.existsById(99L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> customerService.deleteCustomer(99L));
    }
}