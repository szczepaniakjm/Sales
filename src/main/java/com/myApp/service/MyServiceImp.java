package com.myApp.service;

import com.myApp.dao.*;
import com.myApp.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class MyServiceImp implements MyService {
    OrderTabDao orderTabDao = new OrderTabDaoImp();
    ProductDao productDao = new ProductDaoImp();
    CustomerDao customerDao = new CustomerDaoImp();
    CountryDao countryDao = new CountryDaoImp();
    CategoryDao categoryDao = new CategoryDaoImp();
    ProducerDao producerDao = new ProducerDaoImp();

    //Wypisuje produkty posortowane wedlug ilosc zlozonych zamowien dla ich produktow malejaco
    @Override
    public List<Product> listaZamowien() {

                 orderTabDao//bierzemy productId, zliczamy, sortujemy i wypisujemy nazwe
                 .findAll()
                 .stream()                                        //id - ile
                 .collect(Collectors.groupingBy(OrderTab::getProductId, Collectors.counting())) //TU JEST MAPA
                 .entrySet()
                 .stream()
                 .sorted((x1,x2) -> x2.getValue().compareTo(x1.getValue()))
                 .collect(Collectors.toMap(
                         e->productDao.findOne(e.getKey()), e->e.getValue())
                 )
                 .forEach((x,y)-> System.out.println(x +" === "+y))
                 ;
                 return null;

    }

    //Wypisuje kraje posortowane malejaco wedlug ilosci klientow ktorzy z tych krajow pochodza
    @Override
    public void skadKlienci() {
      //  Map<Integer, Long> m1 =
               customerDao
                .findAll()
                .stream()
                .collect(Collectors.groupingBy(Customer::getCountryId, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((x1,x2) -> x2.getValue().compareTo(x1.getValue()))
                .map(p->p.getKey())
                .map(a->countryDao.findOne(a).orElseThrow(() -> new NullPointerException()).getName())
                .forEach(x -> System.out.println(x));
    }

    @Override
    public void addCategory(Category category) {
        categoryDao.add(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryDao.update(category);
    }

    @Override
    public void deleteCategory(Integer integer) {
        long n1 = categoryDao.findAll().stream().count();
        categoryDao.delete(integer);
        long n2 = categoryDao.findAll().stream().count();
        if (n1 > n2) System.out.println("DELETED SUCCESSFULLY");
        else System.out.println("UNABLE TO DELETE");
    }

    @Override
    public void findOneCategoryByID(Integer integer) {
        categoryDao.findOne(integer);
    }

    @Override
    public void findAllCategories() {
        System.out.println(categoryDao.findAll());
    }


    @Override
    public void addCountry(Country country) {
        countryDao.add(country);
    }

    @Override
    public void updateCountry(Country country) {
        countryDao.update(country);
    }

    @Override
    public void deleteCountry(Integer integer) {
        long n1 = countryDao.findAll().stream().count();
        countryDao.delete(integer);
        long n2 = countryDao.findAll().stream().count();
        if (n1 > n2) System.out.println("DELETED SUCCESSFULLY");
        else System.out.println("UNABLE TO DELETE");
    }

    @Override
    public void findOneCountryByID(Integer integer) {
        System.out.println(countryDao.findOne(integer));
    }

    @Override
    public void findAllCountries() {
        System.out.println(countryDao.findAll());
    }

    public void addCustomer(Customer customer) {
        customerDao.add(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public void deleteCustomer(Integer integer) {
        long n1 = customerDao.findAll().stream().count();
        customerDao.delete(integer);
        long n2 = customerDao.findAll().stream().count();
        if (n1 > n2) System.out.println("DELETED SUCCESSFULLY");
        else System.out.println("UNABLE TO DELETE");
    }

    @Override
    public void findOneCustomerByID(Integer integer) {
        System.out.println(customerDao.findOne(integer));
    }

    @Override
    public void findAllCustomers() {
        System.out.println(customerDao.findAll());
    }

    @Override
    public void addOrderTab(OrderTab orderTab) {
        orderTabDao.add(orderTab);
    }

    @Override
    public void updateOrderTab(OrderTab orderTab) {
        orderTabDao.update(orderTab);
    }

    @Override
    public void deleteOrderTab(Integer integer) {
        long n1 = orderTabDao.findAll().stream().count();
        orderTabDao.delete(integer);
        long n2 = orderTabDao.findAll().stream().count();
        if (n1 > n2) System.out.println("DELETED SUCCESSFULLY");
        else System.out.println("UNABLE TO DELETE");
    }

    @Override
    public void findOneOrderTabByID(Integer integer) {
        System.out.println(orderTabDao.findOne(integer));
    }

    @Override
    public void findAllOrderTabs() {
        System.out.println(orderTabDao.findAll());
    }

    @Override
    public void addProducer(Producer producer) {
        producerDao.add(producer);
    }

    @Override
    public void updateProducer(Producer producer) {
        producerDao.update(producer);
    }

    @Override
    public void deleteProducer(Integer integer) {
        long n1 = producerDao.findAll().stream().count();
        producerDao.delete(integer);
        long n2 = producerDao.findAll().stream().count();
        if (n1 > n2) System.out.println("DELETED SUCCESSFULLY");
        else System.out.println("UNABLE TO DELETE");
    }

    @Override
    public void findOneProducerByID(Integer integer) {
        System.out.println(producerDao.findOne(integer));
    }

    @Override
    public void findAllProducers() {
        System.out.println(producerDao.findAll());
    }

    @Override
    public void addProduct(Product product) {
        productDao.add(product);
    }

    @Override
    public void updateProduct(Product product) {
        productDao.update(product);
    }

    @Override
    public void deleteProduct(Integer integer) {
        long number1 = productDao.findAll().stream().count();
        productDao.delete(integer);
        long number2 = productDao.findAll().stream().count();
        if (number1 > number2) System.out.println("DELETED SUCCESSFULLY");
        else System.out.println("UNABLE TO DELETE");
    }

    @Override
    public void findOneProductByID(Integer integer) {
        System.out.println(productDao.findOne(integer));
    }

    @Override
    public void findAllProducts() {
        System.out.println(productDao.findAll());
    }

}
