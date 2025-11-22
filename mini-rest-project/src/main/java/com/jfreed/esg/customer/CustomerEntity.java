package com.jfreed.esg.customer;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class CustomerEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String customerRef;

    private String customerName;

    @Embedded
    private Address address;

    protected CustomerEntity()
    {
    }

    public CustomerEntity(String customerRef, String customerName, Address address)
    {
        this.customerRef = customerRef;
        this.customerName = customerName;
        this.address = address;
    }

    public Long getId()
    {
        return id;
    }

    public String getCustomerRef()
    {
        return customerRef;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public Address getAddress()
    {
        return address;
    }
}
