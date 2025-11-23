package com.jfreed.esg.customer;

import com.jfreed.esg.dto.Customer;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CustomerService
{
    private final WebClient webClient;

    public CustomerService(WebClient webClient)
    {
        this.webClient = webClient;
    }

    public String createCustomer(Customer customer)
    {
        return webClient.post()
                .uri("/api/v1/customers")
                .bodyValue(customer)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public List<Customer> getCustomers()
    {
        return webClient.get()
                .uri("/api/v1/customers")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Customer>>()
                {
                })
                .block();
    }

    public Customer getCustomer(String customerRef)
    {
        return webClient.get()
                .uri("/api/v1/customers?customerRef={ref}", customerRef)
                .retrieve()
                .bodyToMono(Customer.class)
                .block();
    }
}
