package com.jfreed.esg.customer;

import com.jfreed.esg.dto.Customer;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @IterableMapping(qualifiedByName = "mapEntityToDto")
    List<Customer> toDTOs(List<CustomerEntity> entities);

    @Named("mapEntityToDto")
    @Mapping(source = "address.addressLine1", target = "addressLine1")
    @Mapping(source = "address.addressLine2", target = "addressLine2")
    @Mapping(source = "address.town", target = "town")
    @Mapping(source = "address.county", target = "county")
    @Mapping(source = "address.country", target = "country")
    @Mapping(source = "address.postcode", target = "postcode")
    Customer toDTO(CustomerEntity entity);
}
