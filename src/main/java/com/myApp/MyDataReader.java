package com.myApp;

import com.myApp.model.*;
import com.myApp.dao.*;


import java.math.BigDecimal;
import java.util.Optional;

public class MyDataReader {
    static CategoryDao categoryDao = new CategoryDaoImp();
    static CountryDao countryDao = new CountryDaoImp();
    static CustomerDao customerDao = new CustomerDaoImp();
    static OrderTabDao orderTabDao = new OrderTabDaoImp();
    static ProducerDao producerDao = new ProducerDaoImp();
    static ProductDao productDao = new ProductDaoImp();


    public static Category getCategory() {
        System.out.println("Give the id of the Category:");
        Integer categoryId = MyScanner.getInt();
        System.out.println("Give the name of the Category:");
        String categoryName = MyScanner.getString();
        Optional<Category> op =
                categoryDao
                .findAll()
                .stream()
                .filter(x->x.getName().equals(categoryName) && x.getId().equals(categoryId))
                .findFirst()
                ;
        if (op.isPresent()){
            return op.get();
        }
        else {
            Category cat = Category.builder().id(categoryId).name(categoryName).build();
            return cat;
        }
    }

    public static Country getCountry(){

        System.out.println("Give the id of the Country:");
        Integer countryId = MyScanner.getInt();
        System.out.println("Give the name of the Country:");
        String countryName = MyScanner.getString();
        Optional<Country> op =
                countryDao
                        .findAll()
                        .stream()
                        .filter(x->x.getName().equals(countryName) && x.getId().equals(countryId))
                        .findFirst()
                ;
        if (op.isPresent()){
            return op.get();
        }
        else {
            Country cou = Country.builder().id(countryId).name(countryName).build();
            return cou;
        }
    }

    public static Customer getCustomer(){

        System.out.println("Give the id of the Customer:");
        Integer customerId = MyScanner.getInt();
        System.out.println("Give the name of the Customer:");
        String customerName = MyScanner.getString();
        System.out.println("Give the surname of the Customer:");
        String customerSurname = MyScanner.getString();
        System.out.println("Give the age of the Customer:");
        Integer customerAge = MyScanner.getInt();
        System.out.println("Give the id of the Customer's Country:");
        Integer customerCountryId = MyScanner.getInt();

        Optional<Customer> op =
                customerDao
                        .findAll()
                        .stream()
                        .filter(x->x.getId().equals(customerCountryId) && x.getName().equals(customerName)
                                && x.getSurname().equals(customerSurname) && x.getAge().equals(customerAge)
                                && x.getCountryId().equals(customerCountryId))
                        .findFirst()
                ;
        if (op.isPresent()){
            return op.get();
        }
        else {
            Customer cust = Customer.builder().id(customerId).name(customerName)
                    .surname(customerSurname).age(customerAge).countryId(customerCountryId).build();
            return cust;
        }
    }

    public static OrderTab getOrderTab(){
        System.out.println("Give the id of the OrderTab: ");
        Integer ordTabId = MyScanner.getInt();
        System.out.println("Give the Customer id: ");
        Integer ordTabCustomerId = MyScanner.getInt();
        System.out.println("Give the Product id: ");
        Integer ordTabProductId = MyScanner.getInt();
        System.out.println("Give the discount: ");
        BigDecimal ordTabDiscount = MyScanner.getBigDecimal();
        System.out.println("Give the quantity value: ");
        Integer ordTabQuantity = MyScanner.getInt();

        Optional<OrderTab> op =
                orderTabDao
                        .findAll()
                        .stream()
                        .filter(x->x.getId().equals(ordTabId) && x.getCustomerId().equals(ordTabCustomerId)
                                && x.getProductId().equals(ordTabProductId) && x.getDiscount().equals(ordTabDiscount)
                                && x.getQuantity().equals(ordTabQuantity))
                        .findFirst()
                ;
        if (op.isPresent()){

            return op.get();
        }
        else {
            OrderTab ordTab = OrderTab.builder().id(ordTabId).customerId(ordTabCustomerId)
                    .productId(ordTabProductId).discount(ordTabDiscount).quantity(ordTabQuantity).build();
            return ordTab;
        }
    }

    public static Producer getProducer(){
        System.out.println("Give the id of the Producer:");
        Integer producerId = MyScanner.getInt();
        System.out.println("Give the name of the Producer:");
        String producerName = MyScanner.getString();
        System.out.println("Give the id of the Producer's Country:");
        Integer producerCountryId = MyScanner.getInt();
        System.out.println("Give the budget value:");
        BigDecimal producerBudget = MyScanner.getBigDecimal();

        Optional<Producer> op =
                producerDao
                        .findAll()
                        .stream()
                        .filter(x->x.getId().equals(producerId) && x.getName().equals(producerName)
                                && x.getCountryId().equals(producerCountryId) && x.getBudget().equals(producerBudget))
                        .findFirst()
                ;
        if (op.isPresent()){
            return op.get();
        }
        else {
            Producer producer = Producer.builder().id(producerId).name(producerName)
                    .countryId(producerCountryId).budget(producerBudget).build();
            return producer;
        }
    }

    public static Product getProduct() {
        System.out.println("Give the id of the Product:");
        Integer productId = MyScanner.getInt();
        System.out.println("Give the name of the Product:");
        String productName = MyScanner.getString();
        System.out.println("Give the price of the Product:");
        BigDecimal productPrice = MyScanner.getBigDecimal();
        System.out.println("Give the id of Product's Category:");
        Integer productCategoryId = MyScanner.getInt();
        System.out.println("Give the id of Product's Producer:");
        Integer productProducerId = MyScanner.getInt();

        Optional<Product> op =
                productDao
                        .findAll()
                        .stream()
                        .filter(x -> x.getId().equals(productId) && x.getName().equals(productName)
                                && x.getPrice().equals(productPrice) && x.getCategoryId().equals(productCategoryId)
                                && x.getProducerId().equals(productProducerId))
                        .findFirst();
        if (op.isPresent()) {
            return op.get();
        } else {
            Product product = Product.builder().id(productId).name(productName)
                    .price(productPrice).categoryId(productCategoryId).producerId(productProducerId).build();
            return product;
        }
    }
}
