package io.springguru.springrestmvc.controller;

import io.springguru.springrestmvc.entities.Customer;
import io.springguru.springrestmvc.mappers.CustomerMapper;
import io.springguru.springrestmvc.model.CustomerDTO;
import io.springguru.springrestmvc.repositories.CustomerRepository;
import io.springguru.springrestmvc.services.BeerService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerControllerIT {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerController customerController;
    @Autowired
    CustomerMapper customerMapper;

    @Test
    void deleteCustomerIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.deleteById(UUID.randomUUID());
        });
    }

    @Transactional
    @Rollback
    @Test
    void deleteCustomerById() {
        Customer customer = customerRepository.findAll().get(0);
        ResponseEntity responseEntity = customerController.deleteById(customer.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer deleted = customerRepository.findById(customer.getId()).orElse(null);
        assertThat(deleted).isNull();
    }

    @Test
    void updateCustomerNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.updateById(UUID.randomUUID(), CustomerDTO.builder().build());
        });
    }

    @Transactional
    @Rollback
    @Test
    void updateCustomer() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDto = customerMapper.customerToCustomerDto(customer);
        customerDto.setId(null);
        customerDto.setVersion(null);
        customerDto.setCustomerName("New name");
        ResponseEntity responseEntity = customerController.updateById(customer.getId(), customerDto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer updated = customerRepository.findById(customer.getId()).get();
        assertThat(updated.getCustomerName()).isEqualTo(customerDto.getCustomerName());
    }

    @Transactional
    @Rollback
    @Test
    void testCreateCustomer() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .customerName("New Customer")
                .build();
        ResponseEntity responseEntity = customerController.createCustomer(customerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUId = UUID.fromString(locationUUID[locationUUID.length - 1]);

        Customer savedCustomer = customerRepository.findById(savedUUId).get();
        assertThat(savedCustomer).isNotNull();
    }

    @Test
    void listAllCustomers() {
        List<CustomerDTO> dto = customerController.getCustomers();
        assertThat(dto.size()).isEqualTo(2);
    }

    @Test
    void testGetById() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO dto = customerController.getCustomerById(customer.getId());
        assertThat(dto).isNotNull();
    }

    @Test
    void testCustomerIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.getCustomerById(UUID.randomUUID());
        });
    }

    @Transactional
    @Rollback
    @Test
    void testEmptyList() {
        customerRepository.deleteAll();
        List<CustomerDTO> dto = customerController.getCustomers();
        assertThat(dto.size()).isEqualTo(0);
    }
}