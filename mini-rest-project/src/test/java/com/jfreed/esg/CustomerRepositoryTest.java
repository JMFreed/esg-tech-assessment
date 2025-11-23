package com.jfreed.esg;

import com.jfreed.esg.customer.Address;
import com.jfreed.esg.customer.CustomerEntity;
import com.jfreed.esg.customer.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
public class CustomerRepositoryTest
{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager em;

    List<CustomerEntity> customers = List.of(
            new CustomerEntity("12345", "Jane Smith", new Address("Fake House", "Fake St", "Canterbury", "Kent", "United Kingdom", "F4K3 P5T")),
            new CustomerEntity("12346", "Jane Doe", new Address("Fake House", "Fake St", "Leicester", "Leicestershire", "United Kingdom", "F4K3 P5T2")),
            new CustomerEntity("12347", "Sam Adams", new Address("Fake House", "Fake St", "Edinburgh", "Lothian", "United Kingdom", "F4K3 P5T3"))
    );

    @BeforeEach
    public void setup()
    {
        customers.forEach(em::persist);
    }

    @Test
    public void test_findAll()
    {
        List<CustomerEntity> all = customerRepository.findAll();
        assertEquals(3, all.size());
    }

    @Test
    public void test_findByCustomerRef()
    {
        Optional<CustomerEntity> byCustomerRef = customerRepository.findByCustomerRef("12346");
        assertTrue(byCustomerRef.isPresent());
        CustomerEntity customerEntity = byCustomerRef.get();
        assertEquals("Leicester", customerEntity.getAddress().getTown());
    }
}
