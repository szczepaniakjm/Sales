package com.myApp.dao;

import com.myApp.connection.DbConnection;
import com.myApp.model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryDaoImp implements CountryDao {

    private Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void add(Country country) {
        final String sqlInsert = "INSERT INTO Country (name) VALUES (?)";
        try (PreparedStatement prep = connection.prepareStatement(sqlInsert)) {
            prep.setString(1, country.getName());
            prep.execute();
            System.out.println("ADDED SUCCESSFULLY");
        } catch (Exception e) {
            System.err.println("ERROR WHILE ADDING TO TABLE COUNTRY [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
        }
    }

    @Override
    public void update(Country country) {
        final String sqlInsert = "UPDATE Country SET name=? WHERE id=?;";
        try (PreparedStatement prep = connection.prepareStatement(sqlInsert)) {
            prep.setString(1, country.getName());
            prep.setInt(2, country.getId());
            prep.execute();
            System.out.println("UPDATED SUCCESSFULLY");
        } catch (Exception e) {
            System.err.println("ERROR WHILE MODIFYING IN TABLE COUNTRY [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");

        }
    }

    @Override
    public void delete(Integer id) {
        final String sqlDelete = "DELETE FROM Country WHERE id=?";
        try (PreparedStatement prep = connection.prepareStatement(sqlDelete)) {
            prep.setInt(1, id);
            prep.execute();
        } catch (SQLException e) {
            System.err.println("ERROR WHILE DELETING FROM TABLE COUNTRY [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
        }
    }

    @Override
    public Optional<Country> findOne(Integer id) {
        Optional<Country> countryOp = Optional.empty();

        PreparedStatement prep = null;
        ResultSet resultSet = null;

        try {
            final String sql = "SELECT id, name FROM Country WHERE id = ?";
            prep = connection.prepareStatement(sql);
            prep.setInt(1, id);
            resultSet = prep.executeQuery();

            if(resultSet.next())
            {
                countryOp = Optional.of(
                        Country
                                .builder()
                                .id(resultSet.getInt(1))
                                .name(resultSet.getString(2))
                                .build()
                );
            }

        } catch (Exception e) {
            System.err.println("ERROR WHILE SELECTING FROM TABLE COUNTRY, ID = " + id + " [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
        } finally {
            try {
                prep.close();
                resultSet.close();
            } catch (Exception e) {
                System.err.println("ERROR WHILE SELECTING FROM TABLE COUNTRY, ID = " + id + " WHILE CLOSING RESOURCES [ERROR MESSAGE: " + e.getMessage() + ", ERROR CAUSE: " + e.getCause() +  " ]");
            }
        }
        if(!countryOp.isPresent()){
            System.out.println("WRONG ID - NO COUNTRY");
        }
        else System.out.println(countryOp.toString());
        return countryOp;
    }

    @Override
    public List<Country> findAll() {
        List<Country> countries = null;

        try {
            final String sql = "SELECT id, name FROM Country";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            countries = new ArrayList<>();
            while(resultSet.next())
            {
                countries.add(
                        Country
                                .builder()
                                .id(resultSet.getInt(1))
                                .name(resultSet.getString(2))
                                .build()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }
}
