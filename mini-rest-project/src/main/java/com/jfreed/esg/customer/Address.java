package com.jfreed.esg.customer;

import jakarta.persistence.Embeddable;

/*
 * Embeddable Address object, as the address is only pertinent for the time
 * the Customer entity exists
 */
@Embeddable
public class Address
{
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String county;
    private String country;
    private String postcode;

    protected Address()
    {
    }

    public Address(String addressLine1, String addressLine2, String town, String county, String country, String postcode)
    {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.town = town;
        this.county = county;
        this.country = country;
        this.postcode = postcode;
    }

    public String getAddressLine1()
    {
        return addressLine1;
    }

    public String getAddressLine2()
    {
        return addressLine2;
    }

    public String getTown()
    {
        return town;
    }

    public String getCounty()
    {
        return county;
    }

    public String getCountry()
    {
        return country;
    }

    public String getPostcode()
    {
        return postcode;
    }
}
