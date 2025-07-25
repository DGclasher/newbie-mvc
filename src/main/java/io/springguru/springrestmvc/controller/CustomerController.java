package io.springguru.springrestmvc.controller;

import io.springguru.springrestmvc.model.CustomerDTO;
import io.springguru.springrestmvc.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity deleteById(@PathVariable("customerId") UUID customerId) {
        if(!customerService.deleteById(customerId)) {
            throw new NotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/customer/{customerId}")
    public ResponseEntity updateById(@PathVariable("customerId") UUID customerId,@RequestBody CustomerDTO customer) {
        if(customerService.updateById(customerId, customer).isEmpty()) {
            throw new NotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/customer")
    public ResponseEntity createCustomer(@RequestBody CustomerDTO customer) {
        CustomerDTO savedCustomer = customerService.addNewCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedCustomer.getId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<CustomerDTO> getCustomers() {
        return customerService.getCustomers();
    }


    @RequestMapping(value = "/customer/{customerId}", method =  RequestMethod.GET)
    public CustomerDTO getCustomerById(@PathVariable("customerId") UUID customerId) {
        return customerService.getCustomerById(customerId).orElseThrow(NotFoundException::new);
    }
}
