package com.myApp.dao;

import com.myApp.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    void add(Customer customer);
    void update(Customer customer);
    void delete(Integer id);
    Optional<Customer> findOne(Integer id);
    List<Customer> findAll();
}
