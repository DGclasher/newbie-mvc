package io.springguru.springrestmvc.services;

import io.springguru.springrestmvc.model.CustomerDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, CustomerDTO> customers;

    public CustomerServiceImpl() {
        customers = new HashMap<>();
        CustomerDTO cus1 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName("Customer1")
                .createdDate(LocalDate.now())
                .updatedDate(LocalDate.now())
                .build();

        CustomerDTO cus2 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName("Customer2")
                .createdDate(LocalDate.now())
                .updatedDate(LocalDate.now())
                .build();

        customers.put(cus1.getId(), cus1);
        customers.put(cus2.getId(), cus2);
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.ofNullable(customers.get(id));
    }

    @Override
    public CustomerDTO addNewCustomer(CustomerDTO customer) {
        CustomerDTO savedCustomer = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName(customer.getCustomerName())
                .createdDate(LocalDate.now())
                .updatedDate(LocalDate.now())
                .version(1)
                .build();
        customers.put(savedCustomer.getId(), savedCustomer);
        return savedCustomer;
    }

    @Override
    public Optional<CustomerDTO> updateById(UUID customerId, CustomerDTO customer) {
        CustomerDTO existing = customers.get(customerId);
        existing.setCustomerName(customer.getCustomerName());
        existing.setUpdatedDate(LocalDate.now());
        customers.put(customerId, existing);
        return Optional.of(existing);
    }

    @Override
    public Boolean deleteById(UUID customerId) {
        if(customers.containsKey(customerId)) {
            customers.remove(customerId);
        }
        return true;
    }
}
