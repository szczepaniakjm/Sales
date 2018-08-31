package com.myApp.dao;

import com.myApp.connection.DbConnection;
import com.myApp.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoImp implements ProductDao {

    private Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void add(Product product) {
        final String sqlInsert = "INSERT INTO Product (name, price, categoryId, producerId) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement prep = connection.prepareStatement(sqlInsert)) {
            prep.setString(1, product.getName());
            prep.setBigDecimal(2, product.getPrice());
            prep.setInt(3, product.getCategoryId());
            prep.setInt(4, product.getProducerId());
            prep.execute();
        } catch (Exception e) {
            System.err.println("ERROR WHILE ADDING TO TABLE PRODUCT " +
                    "[ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
        }
    }

    @Override
    public void update(Product product) {
        final String sqlInsert = "UPDATE Product SET name=?, price=?, categoryId=?, producerId=? WHERE id=?;";
        try (PreparedStatement prep = connection.prepareStatement(sqlInsert)) {
            prep.setString(1, product.getName());
            prep.setBigDecimal(2, product.getPrice());
            prep.setInt(3, product.getCategoryId());
            prep.setInt(4, product.getProducerId());
            prep.setInt(5, product.getId());
            prep.execute();
        } catch (Exception e) {
            System.err.println("ERROR WHILE MODIFYING IN TABLE PRODUCT " +
                    "[ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
        }
    }

    @Override
    public void delete(Integer id) {
        final String sqlDelete = "DELETE FROM Product WHERE id=?";
        try (PreparedStatement prep = connection.prepareStatement(sqlDelete)) {
            prep.setInt(1, id);
            prep.execute();
        } catch (SQLException e) {
            System.err.println("ERROR WHILE DELETING FROM TABLE Z TABELI PRODUCT " +
                    "[ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
        }
    }

    @Override
    public Optional<Product> findOne(Integer id) {
        Optional<Product> product = Optional.empty();

        PreparedStatement prep = null;
        ResultSet resultSet = null;

        try {
            final String sql = "SELECT id, name, price, categoryId, producerId FROM Product WHERE id = ?";
            prep = connection.prepareStatement(sql);
            prep.setInt(1, id);
            resultSet = prep.executeQuery();

            if(resultSet.next())
            {
                product = Optional.of(
                        Product
                                .builder()
                                .id(resultSet.getInt(1))
                                .name(resultSet.getString(2))
                                .price(resultSet.getBigDecimal(3))
                                .categoryId(resultSet.getInt(4))
                                .producerId(resultSet.getInt(5))
                                .build()
                );
            }

        } catch (Exception e) {
            System.err.println("ERROR WHILE SELECTING FROM TABLE PRODUCT, ID = "
                    + id + " [ERROR MESSAGE: " + e.getMessage()
                    + ", ERROR CAUSE: " + e.getCause() +  " ]");
        } finally {
            try {
                prep.close();
                resultSet.close();
            } catch (Exception e) {
                System.err.println("ERROR WHILE SELECTING FROM TABLE ORDERTAB, ID = " + id + " WHILE CLOSING RESOURCES [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
            }
        }
        if(!product.isPresent()){
            System.out.println("WRONG ID - NO PRODUCT");
        }
        else System.out.println(product.toString());
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = null;

        try {
            final String sql = "SELECT id, name, price, categoryId, producerId FROM Product";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            products = new ArrayList<>();
            while(resultSet.next())
            {
                products.add(
                        Product
                                .builder()
                                .id(resultSet.getInt(1))
                                .name(resultSet.getString(2))
                                .price(resultSet.getBigDecimal(3))
                                .categoryId(resultSet.getInt(4))
                                .producerId(resultSet.getInt(5))
                                .build()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
