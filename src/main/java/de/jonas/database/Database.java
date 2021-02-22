package de.jonas.database;

import lombok.Getter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

    private static final String host = "192.168.178.21";
    private static final String database = "calculator";
    private static final String user = "jonas";
    private static final String password = "password";
    private static final int port = 3306;

    @Getter
    private static Database instance;

    public Statement statement;

    private Connection connection;

    public Database() {
        instance = this;
    }

    @SneakyThrows
    public void connect() {
        if (isConnected()) {
            System.out.println("Database is already connected!");
            System.out.println("Diconnecting...");
            disconnect();
            System.out.println("Diconnected!");
        }
        connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
        statement = getInstance().connection.createStatement();
        System.out.println("Connected!");
    }

    @SneakyThrows
    public void disconnect() {
        if (!isConnected()) {
            System.out.println("Database is already disconnected!");
            return;
        }
        connection.close();
        System.out.println("Disconnected!");
    }

    public boolean isConnected() {
        return connection != null;
    }

    @SneakyThrows
    public boolean isCreated(@NotNull final String table) {
        return Database.getInstance().connection.getMetaData().getTables(null, null, table, null).next();
    }

    @SneakyThrows
    public void createTable(@NotNull final String name, @NotNull final String arguments) {
        statement.executeUpdate("CREATE TABLE " + name + "(" + arguments + ")");
    }

    @SneakyThrows
    public void insert(@NotNull final String table, @NotNull final String values) {
        statement.executeUpdate("INSERT INTO " + table + " VALUES(" + values + ")");
    }

    @SneakyThrows
    public ResultSet get(@NotNull final String arguments) {
        return Database.getInstance().statement.executeQuery(arguments);
    }

}
