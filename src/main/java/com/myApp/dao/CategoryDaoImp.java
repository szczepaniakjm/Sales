package com.myApp.dao;

import com.myApp.connection.DbConnection;
import com.myApp.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDaoImp implements CategoryDao {

    private Connection connection = DbConnection.getInstance().getConnection();


    @Override
    public void add(Category category) {
        final String sqlInsert = "INSERT INTO Category (name) VALUES (?)";
        try (PreparedStatement prep = connection.prepareStatement(sqlInsert)) {
            prep.setString(1, category.getName());//numer znaku zapytania
            prep.execute();
            System.out.println("ADDED SUCCESSFULLY");
        } catch (Exception e) {
            System.err.println("BLAD PODCZAS DODAWANIA WIERSZA DO TABELI CATEGORY [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() + " ]");
        }

    }

    @Override
    public void update(Category category) {
        final String sqlInsert = "UPDATE Category SET name = ? WHERE id = ?;";
        try (PreparedStatement prep = connection.prepareStatement(sqlInsert)) {
            prep.setString(1, category.getName());
            prep.setInt(2, category.getId());
            prep.execute();
            System.out.println("UPDATED SUCCESSFULLY");
        } catch (Exception e) {
            System.err.println("BLAD PODCZAS MODYFIKOWANIA WIERSZA W TABELI CATEGORY [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() + " ]");
        }
    }

    @Override
    public void delete(Integer id) {
        final String sqlDelete = "DELETE FROM Category WHERE id=?";
        try (PreparedStatement prep = connection.prepareStatement(sqlDelete)) {
            prep.setInt(1, id);
            prep.execute();
            System.out.println("DELETED SUCCESSFULLY");
        } catch (SQLException e) {
            System.err.println("BLAD PODCZAS WIERSZA WIERSZA Z TABELI CATEGORY [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() + " ]");
                 }
    }

    @Override
    public Optional<Category> findOne(Integer id) {
        Optional<Category> categoryOp = Optional.empty();

        PreparedStatement prep = null;
        ResultSet resultSet = null;

        try {
            final String sql = "SELECT id, name FROM Category WHERE id = ?";
            prep = connection.prepareStatement(sql);
            prep.setInt(1, id);
            resultSet = prep.executeQuery();

            if (resultSet.next()) {
                categoryOp = Optional.of(
                        Category
                                .builder()
                                .id(resultSet.getInt(1))
                                .name(resultSet.getString(2))
                                .build()
                );
            }

        } catch (Exception e) {
            System.err.println("BLAD PODCZAS POBIERANA WIERSZA Z TABELI CATEGORY O ID = " + id + " [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() + " ]");
        } finally {
            try {
                prep.close();
                resultSet.close();
            } catch (Exception e) {
                System.err.println("BLAD PODCZAS POBIERANA WIERSZA Z TABELI CATEGORY O ID = " + id + " PRZY ZAMYKANIU ZASOBOW [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() + " ]");
            }
        }
        //uwaga - moze zwracac Optional.empty
        return categoryOp;

    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = null;

        try {
            final String sql = "SELECT id, name FROM Category";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            categories = new ArrayList<>();
            while (resultSet.next()) {
                categories.add(
                        Category
                                .builder()
                                .id(resultSet.getInt(1))
                                .name(resultSet.getString(2))
                                .build()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }
}
