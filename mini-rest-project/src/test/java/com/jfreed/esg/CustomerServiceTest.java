package com.jfreed.esg;

import com.jfreed.esg.customer.CustomerService;
import com.jfreed.esg.dto.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CustomerServiceTest
{

    private ExchangeFunction exchangeFunction;

    private CustomerService customerService;

    List<Customer> customers = List.of(
            new Customer("12345", "Jane Smith", "Fake House", "Fake St",
                    "Canterbury", "Kent", "United Kingdom", "F4K3 P5T"),
            new Customer("67890", "Jane Doe", "Real House", "Real St",
                    "London", "Greater London", "UK", "R34L P5T")
    );

    @BeforeEach
    void setup()
    {
        exchangeFunction = mock(ExchangeFunction.class);
        WebClient webClient = WebClient.builder()
                .exchangeFunction(exchangeFunction)
                .build();

        customerService = new CustomerService(webClient);
    }

    @Test
    void testCreateCustomer()
    {
        ClientResponse mockResponse = mock(ClientResponse.class);
        when(mockResponse.statusCode()).thenReturn(HttpStatus.CREATED);
        when(mockResponse.bodyToMono(Customer.class)).thenReturn(Mono.just(customers.get(0)));

        when(exchangeFunction.exchange(any())).thenReturn(Mono.just(mockResponse));

        Customer created = customerService.createCustomer(customers.get(0));

        assertEquals(customers.get(0), created);
        verify(exchangeFunction).exchange(any());
    }

    @Test
    void testGetCustomers()
    {
        ClientResponse mockResponse = mock(ClientResponse.class);
        when(mockResponse.statusCode()).thenReturn(HttpStatus.OK);
        when(mockResponse.bodyToMono(any(ParameterizedTypeReference.class)))
                .thenReturn(Mono.just(customers));

        when(exchangeFunction.exchange(any())).thenReturn(Mono.just(mockResponse));

        List<Customer> result = customerService.getCustomers();

        assertEquals(2, result.size());
        assertEquals("Jane Smith", result.get(0).getCustomerName());
        assertEquals("Jane Doe", result.get(1).getCustomerName());
        verify(exchangeFunction).exchange(any());
    }

    @Test
    void testGetCustomerByRef()
    {
        ClientResponse mockResponse = mock(ClientResponse.class);
        when(mockResponse.statusCode()).thenReturn(HttpStatus.OK);
        when(mockResponse.bodyToMono(Customer.class)).thenReturn(Mono.just(customers.get(0)));

        when(exchangeFunction.exchange(any())).thenReturn(Mono.just(mockResponse));

        Customer result = customerService.getCustomer("12345");

        assertEquals("Jane Smith", result.getCustomerName());
        verify(exchangeFunction).exchange(any());
    }
}