package com.jfreed.esg;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfreed.esg.customer.Address;
import com.jfreed.esg.customer.CustomerController;
import com.jfreed.esg.customer.CustomerEntity;
import com.jfreed.esg.customer.CustomerRepository;
import com.jfreed.esg.dto.Customer;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
@ActiveProfiles("test")
class CustomerControllerTest
{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void testCreateCustomer() throws Exception
    {
        Customer customer = new Customer("12345", "Jone Smith", "Fake House", "Fake St", "Canterbury", "Kent", "United Kingdom", "F4K3 P5T");
        CustomerEntity entity = new CustomerEntity("12345", "Jone Smith", new Address("Fake House", "Fake St", "Canterbury", "Kent", "United Kingdom", "F4K3 P5T"));
        when(customerRepository.save(any())).thenReturn(entity);
        mockMvc.perform(post("/api/v1/customers")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerRef").value("12345"));
    }
}