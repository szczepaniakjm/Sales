package com.myApp.dao;

import com.myApp.model.Country;


import java.util.List;
import java.util.Optional;

public interface CountryDao {
    void add(Country country);
    void update(Country country);
    void delete(Integer id);
    Optional<Country> findOne(Integer id);
    List<Country> findAll();
}
