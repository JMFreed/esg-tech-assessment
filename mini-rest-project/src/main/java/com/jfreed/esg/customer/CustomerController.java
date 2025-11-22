package com.jfreed.esg.customer;

import com.jfreed.esg.dto.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController
{
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public ResponseEntity<?> getCustomers(@RequestParam(value = "customerRef", required = false) String customerRef)
    {
        if (customerRef != null && !customerRef.isEmpty())
        {
            Optional<CustomerEntity> byCustomerRef = customerRepository.findByCustomerRef(customerRef);
            if (byCustomerRef.isEmpty())
            {
                return ResponseEntity.notFound().build();
            } else
            {
                return ResponseEntity.ok().body(byCustomerRef.get());
            }
        }
        List<CustomerEntity> all = customerRepository.findAll();
        return ResponseEntity.ok().body(all);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer)
    {
        Address address = new Address(customer.getAddressLine1(), customer.getAddressLine2(),
                customer.getTown(), customer.getCounty(),
                customer.getCountry(), customer.getPostcode());
        CustomerEntity entity = new CustomerEntity(customer.getCustomerRef(), customer.getCustomerName(), address);
        CustomerEntity saved = customerRepository.save(entity);
        return ResponseEntity.created(URI.create("/api/v1/customers/" + saved.getId()))
                .body(saved);
    }
}
