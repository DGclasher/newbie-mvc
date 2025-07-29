package io.springguru.springrestmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.springguru.springrestmvc.model.CustomerDTO;
import io.springguru.springrestmvc.services.CustomerService;
import io.springguru.springrestmvc.services.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    WebApplicationContext wac;
    @MockitoBean
    CustomerService customerService;

    CustomerServiceImpl customerServiceImpl =  new CustomerServiceImpl();

    @Autowired
    private ObjectMapper objectMapper;

    private final String USERNAME = "user1";
    private final String PASSWORD = "password";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
        customerServiceImpl = new CustomerServiceImpl();
    }

    @Test
    void testDeleteCustomer() throws Exception {
        CustomerDTO customer = customerServiceImpl.getCustomers().get(0);
        given(customerService.deleteById(any())).willReturn(true);
        mockMvc.perform(delete("/api/v1/customer/" + customer.getId())
                        .with(csrf())
                        .with(httpBasic(USERNAME, PASSWORD))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        ArgumentCaptor<UUID> captor = ArgumentCaptor.forClass(UUID.class);
        verify(customerService).deleteById(captor.capture());
        assertThat(captor.getValue()).isEqualTo(customer.getId());
    }

    @Test
    void testUpdateCustomer() throws Exception {
        CustomerDTO customer = customerServiceImpl.getCustomers().get(0);

        mockMvc.perform(put("/api/v1/customer/" + customer.getId())
                .with(httpBasic(USERNAME, PASSWORD))
                        .with(csrf())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)));

        verify(customerService).updateById(any(UUID.class), any(CustomerDTO.class));
    }

    @Test
    void createCustomer() throws Exception {
        CustomerDTO customer = customerServiceImpl.getCustomers().get(0);

        given(customerService.addNewCustomer(any(CustomerDTO.class))).willReturn(customerServiceImpl.getCustomers().get(0));

        mockMvc.perform(post("/api/v1/customer")
                        .with(httpBasic(USERNAME, PASSWORD))
                        .with(csrf())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void getCustomers() throws Exception {
        given(customerService.getCustomers()).willReturn(customerServiceImpl.getCustomers());
        mockMvc.perform(get("/api/v1/customers")
                        .with(httpBasic(USERNAME, PASSWORD))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)));
    }

    @Test
    void getCustomerByIdNotFound() throws Exception {
        given(customerService.getCustomerById(any(UUID.class))).willThrow(NotFoundException.class);
        mockMvc.perform(get("/api/v1/customer/" + UUID.randomUUID())
                        .with(httpBasic(USERNAME, PASSWORD)))
                .andExpect(status().isNotFound());
    }

    @Test
    void getCustomerById() throws Exception {
        CustomerDTO testCustomer = customerServiceImpl.getCustomers().getFirst();
        given(customerService.getCustomerById(testCustomer.getId())).willReturn(Optional.of(testCustomer));
        mockMvc.perform(get("/api/v1/customer/" + testCustomer.getId())
                        .with(httpBasic(USERNAME, PASSWORD))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testCustomer.getId().toString())));
    }
}