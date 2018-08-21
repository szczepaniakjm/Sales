package com.myApp.service;

import com.myApp.connection.DbStatus;
import com.myApp.dao.*;
import com.myApp.model.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MyServiceImp implements MyService {
    OrderTabDao orderTabDao = new OrderTabDaoImp();
    ProductDao productDao = new ProductDaoImp();
    CustomerDao customerDao = new CustomerDaoImp();
    CountryDao countryDao = new CountryDaoImp();
    CategoryDao categoryDao = new CategoryDaoImp();
    ProducerDao producerDao = new ProducerDaoImp();

    //      1. Wypisac produkty posortowane wedlug ilosc zlozonych zamowien dlaich produktow malejaco
    @Override
    public List<Product> listaZamowien() {

 //        List<OrderTab> orderTabList =
                 orderTabDao

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
//bierzemy productId, zliczamy, sortujemy i wypisujemy nazwe
    }

    //  2. Wypisac kraje posortowane malejaco wedlug ilosci klientow ktorzy z tych krajow pochodza
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
            //    .collect(Collectors.toList());
    }


    //      3. Wypisac nazwe klienta ktory wydal najwiecej na zakupy
    //OrderTab: klient, produkt i discount
    //dla customerId bierzemy Produkt.price po productId, * discount
    @Override
    public Optional<Customer> najwiecejWydal() {
/*
        orderTabDao.findAll()
                .stream()
                .collect(Collectors.groupingBy(OrderTab::getCustomerId).//.productDao.findOne(OrderTab::getProductId)););
                ;

 */               
        return Optional.empty();
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
        categoryDao.delete(integer);
    }

    @Override
    public void findOneCategoryByID(Integer integer) {
        System.out.println(categoryDao.findOne(integer));
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
        countryDao.delete(integer);
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
        customerDao.delete(integer);
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
        orderTabDao.delete(integer);
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
        producerDao.delete(integer);
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
        productDao.delete(integer);
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
