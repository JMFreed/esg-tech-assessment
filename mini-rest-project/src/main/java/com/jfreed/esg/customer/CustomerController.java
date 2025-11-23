package com.jfreed.esg.customer;

import com.jfreed.esg.config.ApiPaths;
import com.jfreed.esg.dto.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiPaths.CUSTOMERS)
public class CustomerController
{
    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public CustomerController(CustomerRepository customerRepository, CustomerMapper customerMapper)
    {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
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
            }
            else
            {
                Customer dto = customerMapper.toDTO(byCustomerRef.get());
                return ResponseEntity.ok().body(dto);
            }
        }
        List<CustomerEntity> all = customerRepository.findAll();
        List<Customer> dtos = customerMapper.toDTOs(all);
        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer)
    {
        Address address = new Address(customer.getAddressLine1(), customer.getAddressLine2(),
                customer.getTown(), customer.getCounty(),
                customer.getCountry(), customer.getPostcode());
        CustomerEntity entity = new CustomerEntity(customer.getCustomerRef(), customer.getCustomerName(), address);
        CustomerEntity saved = customerRepository.save(entity);
        Customer dto = customerMapper.toDTO(saved);
        return ResponseEntity.created(URI.create(ApiPaths.CUSTOMERS + "/" + saved.getId()))
                .body(dto);
    }
}
