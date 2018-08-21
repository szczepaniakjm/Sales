package com.myApp.dao;

import com.myApp.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    void add(Product product);
    void update(Product product);
    void delete(Integer id);
    Optional<Product> findOne(Integer id);
    List<Product> findAll();
}
