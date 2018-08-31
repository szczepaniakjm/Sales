package com.myApp.service;

import com.myApp.model.*;

import java.util.List;
import java.util.Optional;

public interface MyService {
    List<Product> listaZamowien();
    void skadKlienci();

    void addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Integer integer);
    void findOneCategoryByID(Integer integer);
    void findAllCategories();

    void addCountry(Country country);
    void updateCountry(Country country);
    void deleteCountry(Integer integer);
    void findOneCountryByID(Integer integer);
    void findAllCountries();

    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(Integer integer);
    void findOneCustomerByID(Integer integer);
    void findAllCustomers();

    void addOrderTab(OrderTab orderTab);
    void updateOrderTab(OrderTab orderTab);
    void deleteOrderTab(Integer integer);
    void findOneOrderTabByID(Integer integer);
    void findAllOrderTabs();

    void addProducer(Producer producer);
    void updateProducer(Producer producer);
    void deleteProducer(Integer integer);
    void findOneProducerByID(Integer integer);
    void findAllProducers();

    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Integer integer);
    void findOneProductByID(Integer integer);
    void findAllProducts();

}