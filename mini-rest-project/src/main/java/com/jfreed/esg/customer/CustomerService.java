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

    private final CustomerMapper customerMapper;

    public CustomerService(WebClient webClient, CustomerMapper customerMapper)
    {
        this.webClient = webClient;
        this.customerMapper = customerMapper;
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
        List<CustomerEntity> entities = webClient.get()
                .uri("/api/v1/customers")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CustomerEntity>>()
                {
                })
                .block();

        return customerMapper.toDTOs(entities);
    }

    public Customer getCustomer(String customerRef)
    {
        CustomerEntity entity = webClient.get()
                .uri("/api/v1/customers?customerRef={ref}", customerRef)
                .retrieve()
                .bodyToMono(CustomerEntity.class)
                .block();

        return customerMapper.toDTO(entity);
    }
}
