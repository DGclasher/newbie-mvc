package io.springguru.springrestmvc.repositories;

import io.springguru.springrestmvc.entities.Beer;
import io.springguru.springrestmvc.entities.BeerOrder;
import io.springguru.springrestmvc.entities.BeerOrderShipment;
import io.springguru.springrestmvc.entities.Customer;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerOrderRepositoryTest {
    @Autowired
    BeerOrderRepository beerOrderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    BeerRepository beerRepository;

    Customer testCustomer;
    Beer testBeer;

    @BeforeEach
    void setUp() {
        testCustomer = customerRepository.findAll().get(0);
        testBeer = beerRepository.findAll().get(0);
    }

    @Test
    @Transactional
    void testBeerOrders() {
        BeerOrder beerOrder = BeerOrder.builder()
                .customerRef("Test Customer")
                .customer(testCustomer)
                .beerOrderShipment(BeerOrderShipment.builder()
                        .trackingNumber("1234f")
                        .build())
                .build();

        BeerOrder savedBeerOrder = beerOrderRepository.save(beerOrder);
        System.out.println(savedBeerOrder.getCustomerRef());
    }
}