package com.java_dapps.store.repository;

import com.java_dapps.store.entity.Customer;
import com.java_dapps.store.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findOneByUserId(long userId);
}
