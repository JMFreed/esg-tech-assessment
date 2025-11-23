package com.jfreed.esg;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfreed.esg.customer.Address;
import com.jfreed.esg.customer.CustomerController;
import com.jfreed.esg.customer.CustomerEntity;
import com.jfreed.esg.customer.CustomerRepository;
import com.jfreed.esg.dto.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @MockBean
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

    @Test
    void testGetAllCustomers() throws Exception
    {
        List<CustomerEntity> customers = List.of(
                new CustomerEntity("12345", "Jone Smith", new Address("Fake House", "Fake St", "Canterbury", "Kent", "United Kingdom", "F4K3 P5T")),
                new CustomerEntity("12346", "Jane Doe", new Address("Fake House", "Fake St", "Leicester", "Leicestershire", "United Kingdom", "F4K3 P5T2")),
                new CustomerEntity("12347", "Laura Parker", new Address("Fake House", "Fake St", "Edinburgh", "Lothian", "United Kingdom", "F4K3 P5T3"))
        );
        when(customerRepository.findAll()).thenReturn(customers);
        mockMvc.perform(get("/api/v1/customers")
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerRef").value("12345"));
    }

    @Test
    void testGetCustomersByRef() throws Exception
    {
        List<CustomerEntity> customers = List.of(
                new CustomerEntity("12345", "Jone Smith", new Address("Fake House", "Fake St", "Canterbury", "Kent", "United Kingdom", "F4K3 P5T")),
                new CustomerEntity("12346", "Jane Doe", new Address("Fake House", "Fake St", "Leicester", "Leicestershire", "United Kingdom", "F4K3 P5T2")),
                new CustomerEntity("12347", "Laura Parker", new Address("Fake House", "Fake St", "Edinburgh", "Lothian", "United Kingdom", "F4K3 P5T3"))
        );
        when(customerRepository.findByCustomerRef("12347")).thenReturn(Optional.ofNullable(customers.get(2)));
        String customerRef = "12347";
        mockMvc.perform(get("/api/v1/customers?customerRef={ref}", customerRef)
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerRef").value("12347"))
                .andExpect(jsonPath("$.customerName").value("Laura Parker"));
    }
}