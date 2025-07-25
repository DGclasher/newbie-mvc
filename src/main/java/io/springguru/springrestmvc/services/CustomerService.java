package io.springguru.springrestmvc.services;

import io.springguru.springrestmvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    List<CustomerDTO> getCustomers();
    Optional<CustomerDTO> getCustomerById(UUID id);
    CustomerDTO addNewCustomer(CustomerDTO customer);
    Optional<CustomerDTO> updateById(UUID customerId, CustomerDTO customer);
    Boolean deleteById(UUID customerId);
}
