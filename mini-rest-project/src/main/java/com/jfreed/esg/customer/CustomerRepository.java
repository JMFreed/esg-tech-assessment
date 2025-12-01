package com.jfreed.esg.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
 * TODO: Use JpaSpecificationExecutor<T> and Specification<T>
 *  and call List<T> findAll(Specification<T> spec) to allow filtering by other fields
 */
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>
{
    Optional<CustomerEntity> findByCustomerRef(String customerRef);
}
