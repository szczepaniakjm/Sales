package com.myApp.dao;

import com.myApp.model.Producer;

import java.util.List;
import java.util.Optional;

public interface ProducerDao {
    void add(Producer producer);
    void update(Producer producer);
    void delete(Integer id);
    Optional<Producer> findOne(Integer id);
    List<Producer> findAll();
    Optional<Producer> findByName(String name);
}
