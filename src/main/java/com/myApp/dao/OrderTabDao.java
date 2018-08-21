package com.myApp.dao;

import com.myApp.model.OrderTab;

import java.util.List;
import java.util.Optional;

public interface OrderTabDao {
    void add(OrderTab orderTab);
    void update(OrderTab orderTab);
    void delete(Integer id);
    Optional<OrderTab> findOne(Integer id);
    List<OrderTab> findAll();
}
