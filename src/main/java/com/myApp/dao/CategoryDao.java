package com.myApp.dao;

import com.myApp.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {
    void add(Category category);
    void update(Category category);
    void delete(Integer id);
    Optional<Category> findOne(Integer id);
    List<Category> findAll();
}
