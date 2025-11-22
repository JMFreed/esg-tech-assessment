package com.jfreed.esg.customer;

import com.jfreed.esg.dto.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController
{
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer)
    {
        Address address = new Address(customer.getAddressLine1(),
                customer.getAddressLine2(),
                customer.getTown(),
                customer.getCounty(),
                customer.getCountry(),
                customer.getPostcode());
        CustomerEntity entity = new CustomerEntity(
                customer.getCustomerRef(),
                customer.getCustomerName(),
                address
        );
        CustomerEntity saved = customerRepository.save(entity);
        return ResponseEntity.created(URI.create("/api/v1/customers/" + saved.getId()))
                .body(saved);
    }
}
