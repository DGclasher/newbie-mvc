package io.springguru.springrestmvc.mappers;

import io.springguru.springrestmvc.entities.Customer;
import io.springguru.springrestmvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer customerDtoToCustomer(CustomerDTO dto);
    CustomerDTO customerToCustomerDto(Customer customer);
}
