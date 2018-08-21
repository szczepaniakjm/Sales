package com.myApp.dao;

import com.myApp.connection.DbConnection;
import com.myApp.model.Producer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProducerDaoImp implements ProducerDao {

    private Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void add(Producer producer) {
        final String sqlInsert = "INSERT INTO Producer (name, countryId, budget) VALUES (?, ?, ?)";
        try (PreparedStatement prep = connection.prepareStatement(sqlInsert)) {
            prep.setString(1, producer.getName());
            prep.setInt(2, producer.getCountryId());
            prep.setBigDecimal(3, producer.getBudget());
            prep.execute();
            System.out.println("ADDED SUCCESSFULLY");
        } catch (Exception e) {
            System.err.println("BLAD PODCZAS DODAWANIA WIERSZA DO TABELI PRODUCER [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
        }
    }

    @Override
    public void update(Producer producer) {
        final String sqlInsert = "UPDATE Producer SET name=?, countryId=?, budget=? WHERE id=?;";
        try (PreparedStatement prep = connection.prepareStatement(sqlInsert)) {
            prep.setString(1, producer.getName());
            prep.setInt(2, producer.getCountryId());
            prep.setBigDecimal(3, producer.getBudget());
            prep.setInt(4, producer.getId());
            prep.execute();
            System.out.println("UPDATED SUCCESSFULLY");
        } catch (Exception e) {
            System.err.println("BLAD PODCZAS MODYFIKOWANIA WIERSZA W TABELI PRODUCER [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
        }
    }

    @Override
    public void delete(Integer id) {
        final String sqlDelete = "DELETE FROM Producer WHERE id=?";
        try (PreparedStatement prep = connection.prepareStatement(sqlDelete)) {
            prep.setInt(1, id);
            prep.execute();
            System.out.println("DELETED SUCCESSFULLY");
        } catch (SQLException e) {
            System.err.println("BLAD PODCZAS WIERSZA WIERSZA Z TABELI PRODUCER [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
        }
    }

    @Override
    public Optional<Producer> findOne(Integer id) {
        Optional<Producer> producer = Optional.empty();

        PreparedStatement prep = null;
        ResultSet resultSet = null;

        try {
            final String sql = "SELECT id, name, countryId, budget FROM Producer WHERE id = ?";
            prep = connection.prepareStatement(sql);
            prep.setInt(1, id);
            resultSet = prep.executeQuery();

            if(resultSet.next())
            {
                producer = Optional.of(
                        Producer
                                .builder()
                                .id(resultSet.getInt(1))
                                .name(resultSet.getString(2))
                                .countryId(resultSet.getInt(3))
                                .budget(resultSet.getBigDecimal(4))
                                .build()
                );
            }

        } catch (Exception e) {
            System.err.println("BLAD PODCZAS POBIERANA WIERSZA Z TABELI PRODUCER O ID = " + id + " [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
        } finally {
            try {
                prep.close();
                resultSet.close();
            } catch (Exception e) {
                System.err.println("BLAD PODCZAS POBIERANA WIERSZA Z TABELI PRODUCER O ID = " + id + " PRZY ZAMYKANIU ZASOBOW [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
            }
        }
        return producer;
    }

    @Override
    public List<Producer> findAll() {
        List<Producer> producers = null;

        try {
            final String sql = "SELECT id, name, countryId, budget FROM Producer";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            producers = new ArrayList<>();
            while(resultSet.next())
            {
                producers.add(
                        Producer
                                .builder()
                                .id(resultSet.getInt(1))
                                .name(resultSet.getString(2))
                                .countryId(resultSet.getInt(3))
                                .budget(resultSet.getBigDecimal(4))
                                .build()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producers;
    }

    @Override
    public Optional<Producer> findByName(String name) {
        Optional<Producer> producerOp = Optional.empty();

        try {
            final String sql = "SELECT id, name, countryId, budget FROM Producer WHERE name = ?";
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setString(1, name);
            ResultSet resultSet = prep.executeQuery();

            if(resultSet.next())
            {
                producerOp = Optional.of(
                        Producer
                                .builder()
                                .id(resultSet.getInt(1))
                                .name(resultSet.getString(2))
                                .countryId(resultSet.getInt(3))
                                .budget(resultSet.getBigDecimal(4))
                                .build()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producerOp;
    }
}
