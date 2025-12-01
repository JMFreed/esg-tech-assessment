package com.jfreed.esg.customer;

import com.jfreed.esg.config.ApiPaths;
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

    public Customer createCustomer(Customer customer)
    {
        return webClient.post()
                .uri(ApiPaths.CUSTOMERS)
                .bodyValue(customer)
                .retrieve()
                .bodyToMono(Customer.class)
                .block();
    }

    public List<Customer> getCustomers()
    {
        return webClient.get()
                .uri(ApiPaths.CUSTOMERS)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Customer>>()
                {
                })
                .block();
    }

    /*
     * TODO: probably want a method that takes Map<String, String> params
     *  to omit having to hardcode query params in the URI
     */
    public Customer getCustomer(String customerRef)
    {
        return webClient.get()
                .uri(ApiPaths.CUSTOMERS + "?customerRef={ref}", customerRef)
                .retrieve()
                .bodyToMono(Customer.class)
                .block();
    }
}
