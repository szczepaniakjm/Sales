package com.myApp.dao;

import com.myApp.connection.DbConnection;
import com.myApp.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDaoImp implements CustomerDao {

    private Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void add(Customer customer) {
        final String sqlInsert = "INSERT INTO Customer (name, surname, age, countryId) VALUES (?, ?, ?, ?)";
        try (PreparedStatement prep = connection.prepareStatement(sqlInsert)) {
            prep.setString(1, customer.getName());
            prep.setString(2, customer.getSurname());
            prep.setInt(3, customer.getAge());
            prep.setInt(4, customer.getCountryId());
            prep.execute();
        } catch (Exception e) {
            System.err.println("ERROR WHILE ADDING TO TABLE CUSTOMER [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
        }
    }

    @Override
    public void update(Customer customer) {
        final String sqlInsert = "UPDATE Customer SET name=?, surname=?, age=?, countryId=? WHERE id=?;";
        try (PreparedStatement prep = connection.prepareStatement(sqlInsert)) {
            prep.setString(1, customer.getName());
            prep.setString(2, customer.getSurname());
            prep.setInt(3, customer.getAge());
            prep.setInt(4, customer.getCountryId());
            prep.setInt(5, customer.getId());
            prep.execute();
        } catch (Exception e) {
            System.err.println("ERROR WHILE MODIFYING IN TABLE CUSTOMER [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
        }
    }

    @Override
    public void delete(Integer id) {
        final String sqlDelete = "DELETE FROM Customer WHERE id=?";
        try (PreparedStatement prep = connection.prepareStatement(sqlDelete)) {
            prep.setInt(1, id);
            prep.execute();
        } catch (SQLException e) {
            System.err.println("ERROR WHILE DELETING FROM TABLE CUSTOMER [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
        }
    }

    @Override
    public Optional<Customer> findOne(Integer id) {
        Optional<Customer> customerOp = Optional.empty();

        PreparedStatement prep = null;
        ResultSet resultSet = null;

        try {
            final String sql = "SELECT id, name, surname, age, countryId FROM Customer WHERE id = ?";
            prep = connection.prepareStatement(sql);
            prep.setInt(1, id);
            resultSet = prep.executeQuery();

            if(resultSet.next())
            {
                customerOp = Optional.of(
                        Customer
                                .builder()
                                .id(resultSet.getInt(1))
                                .name(resultSet.getString(2))
                                .surname(resultSet.getString(3))
                                .age(resultSet.getInt(4))
                                .countryId(resultSet.getInt(5))
                                .build()
                );
            }

        } catch (Exception e) {
            System.err.println("ERROR WHILE SELECTING FROM TABLE CUSTOMER, ID = " + id + " [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
        } finally {
            try {
                prep.close();
                resultSet.close();
            } catch (Exception e) {
                System.err.println("ERROR WHILE SELECTING FROM TABLE CUSTOMER, ID = " + id + " WHILE CLOSING RESOURCES [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
            }
        }
        if(!customerOp.isPresent()){
            System.out.println("WRONG ID - NO CUSTOMER");
        }
        else System.out.println(customerOp.toString());
        return customerOp;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = null;

        try {
            final String sql = "SELECT id, name, surname, age, countryId FROM Customer";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            customers = new ArrayList<>();
            while(resultSet.next())
            {
                customers.add(
                        Customer
                                .builder()
                                .id(resultSet.getInt(1))
                                .name(resultSet.getString(2))
                                .surname(resultSet.getString(3))
                                .age(resultSet.getInt(4))
                                .countryId(resultSet.getInt(5))
                                .build()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

}
