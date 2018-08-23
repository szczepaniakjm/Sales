package com.myApp.dao;

import com.myApp.connection.DbConnection;
import com.myApp.model.OrderTab;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderTabDaoImp implements OrderTabDao {

    private Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void add(OrderTab orderTab) {
        final String sqlInsert = "INSERT INTO OrderTab (customerId, productId, discount, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement prep = connection.prepareStatement(sqlInsert)) {
            prep.setInt(1, orderTab.getCustomerId());
            prep.setInt(2, orderTab.getProductId());
            prep.setBigDecimal(3,orderTab.getDiscount());
            prep.setInt(4, orderTab.getQuantity());
            prep.execute();
            System.out.println("ADDED SUCCESSFULLY");
        } catch (Exception e) {
            System.err.println("BLAD PODCZAS DODAWANIA WIERSZA DO TABELI ORDERTAB [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
        }
    }

    @Override
    public void update(OrderTab orderTab) {
        final String sqlInsert = "UPDATE OrderTab SET customerId=?, productId=?, discount=?, quantity=? WHERE id=?;";
        try (PreparedStatement prep = connection.prepareStatement(sqlInsert)) {
            prep.setInt(1, orderTab.getCustomerId());
            prep.setInt(2, orderTab.getProductId());
            prep.setBigDecimal(3, orderTab.getDiscount());
            prep.setInt(4, orderTab.getQuantity());
            prep.setInt(5, orderTab.getId());
            prep.execute();
            System.out.println("UPDATED SUCCESSFULLY");
        } catch (Exception e) {
            System.err.println("BLAD PODCZAS MODYFIKOWANIA WIERSZA W TABELI ORDERTAB [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
        }
    }

    @Override
    public void delete(Integer id) {
        final String sqlDelete = "DELETE FROM OrderTab WHERE id=?";
        try (PreparedStatement prep = connection.prepareStatement(sqlDelete)) {
            prep.setInt(1, id);
            prep.execute();
            System.out.println("DELETED SUCCESSFULLY");
        } catch (SQLException e) {
            System.err.println("BLAD PODCZAS WIERSZA WIERSZA Z TABELI ORDERTAB [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
        }

    }

    @Override
    public Optional<OrderTab> findOne(Integer id) {
        Optional<OrderTab> orderTab = Optional.empty();

        PreparedStatement prep = null;
        ResultSet resultSet = null;

        try {
            final String sql = "SELECT id, customerId, productId, discount, quantity FROM OrderTab WHERE id = ?";
            prep = connection.prepareStatement(sql);
            prep.setInt(1, id);
            resultSet = prep.executeQuery();

            if(resultSet.next())
            {
                orderTab = Optional.of(
                        OrderTab
                                .builder()
                                .id(resultSet.getInt(1))
                                .customerId(resultSet.getInt(2))
                                .productId(resultSet.getInt(3))
                                .discount(resultSet.getBigDecimal(4))
                                .quantity(resultSet.getInt(5))
                                .build()
                );
            }

        } catch (Exception e) {
            System.err.println("BLAD PODCZAS POBIERANA WIERSZA Z TABELI ORDERTAB O ID = " + id + " [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
        } finally {
            try {
                prep.close();
                resultSet.close();
            } catch (Exception e) {
                System.err.println("BLAD PODCZAS POBIERANA WIERSZA Z TABELI ORDERTAB O ID = " + id + " PRZY ZAMYKANIU ZASOBOW [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
            }
        }
        if(!orderTab.isPresent()){
            System.out.println("WRONG ID - NO ORDERTAB");
        }
        else System.out.println(orderTab.toString());
        return orderTab;
    }

    @Override
    public List<OrderTab> findAll() {
        List<OrderTab> orderTabs = null;

        try {
            final String sql = "SELECT id, customerId, productId, discount, quantity FROM OrderTab";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            orderTabs = new ArrayList<>();
            while(resultSet.next())
            {
                orderTabs.add(
                        OrderTab
                                .builder()
                                .id(resultSet.getInt(1))
                                .customerId(resultSet.getInt(2))
                                .productId(resultSet.getInt(3))
                                .discount(resultSet.getBigDecimal(4))
                                .quantity(resultSet.getInt(5))
                                .build()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderTabs;
    }
}
