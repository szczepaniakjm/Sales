package com.myApp.connection;

import com.myApp.connection.builders.SqlTable;
import com.myApp.exceptions.MyException;
import com.myApp.model.Category;
import com.myApp.model.OrderTab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DbConnection {
    private static DbConnection ourInstance = new DbConnection();

    public static DbConnection getInstance() {
        return ourInstance;
    }

    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DATABASE = "jdbc:sqlite:Source.db";

    private Connection connection; //obiekt reprezentujacy polaczenie z db

    public Connection getConnection() {
        return connection;
    }

    private DbConnection() {
        try {
            Class.forName(DRIVER);//zaladowanie sterownika
            connection = DriverManager.getConnection(DATABASE);//inicjalizacja polaczenia
            System.out.println("PRAWIDLOWO UTWORZONO TABELE!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("BLAD KOMUNIKACJI Z DB", LocalDateTime.now());
        }
    }

    public void createTables() {
        try (Statement stat = connection.createStatement()) {
            List<String> commands = new ArrayList<>();

            commands.add(
                    SqlTable.builder()
                            .table("Category")
                            .primaryKey("id")
                            .stringColumn("name", 50, "not null")
                            .build()
                            .command()
            );

            commands.add(
                    SqlTable.builder()
                            .table("Country")
                            .primaryKey("id")
                            .stringColumn("name", 50, "not null")
                            .build()
                            .command()
            );

            commands.add(
                    SqlTable.builder()
                            .table("Customer")
                            .primaryKey("id")
                            .stringColumn("name", 50, "not null")
                            .stringColumn("surname", 50, "not null")
                            .intColumn("age", "")
                            .foreignKey("countryId", "Country", "id", "on delete cascade")
                            .build()
                            .command()
            );

            commands.add(
                    SqlTable.builder()
                            .table("Producer")
                            .primaryKey("id")
                            .stringColumn("name", 50, "not null")
                            .decimalColumn("budget", 7, 3, "")
                            .foreignKey("countryId", "Country", "id", "on delete cascade")
                            .build()
                            .command()
            );

            commands.add(
                    SqlTable.builder()
                            .table("Product")
                            .primaryKey("id")
                            .stringColumn("name", 50, "not null")
                            .decimalColumn("price", 9, 2, "")
                            .foreignKey("categoryId", "Category", "id", "on delete cascade")
                            .foreignKey("producerId", "Producer", "id", "on delete cascade")
                            .build()
                            .command()
            );

            commands.add(
                    SqlTable.builder()
                            .table("OrderTab")
                            .primaryKey("id")
                            .decimalColumn("discount", 5, 2, "check (0 <= discount < 100)")
                            .intColumn("quantity", "check (0 < quantity < 6)")
                            .foreignKey("customerId", "Customer", "id", "on delete cascade")
                            .foreignKey("productId", "Product", "id", "on delete cascade")
                            .build()
                            .command()
            );

            for (String command : commands) {
                stat.execute(command);
            }


        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("BLAD PODCZAS TWORZENIA TABEL", LocalDateTime.now());
        }
    }
}
