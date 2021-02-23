package de.jonas.database;

import lombok.Getter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Alle nötigen Methoden um die Verbindung zu der Datenbank herzustellen und mit ihr zu interagieren.
 */
public class Database {

    /** Die IP bzw. die Adresse der Datenbank. */
    private static final String HOST = "192.168.178.21";
    /** Der Name der Datenbank. */
    private static final String DATABASE = "calculator";
    /** Der Nutzer, mit dem sich das Programm in der Datenbank anmeldet. */
    private static final String USER = "jonas";
    /** Das Passwort des Nutzers, mit dem sich das Programm in der Datenbank anmeldet. */
    private static final String PASSWORD = "password";
    /** Der Port, auf dem die Datenbank erreichbar ist. */
    private static final int PORT = 3306;

    /** Die Instanz, mit der man auf die {@link Database Datenbank} zugreifen kann. */
    @Getter
    private static Database instance;

    /** Das {@link Statement statement} womit mit der Datenbank interagiert wird. */
    private Statement statement;
    /** Die {@link Connection Verbindung} zur Datenbank. */
    private Connection connection;

    /**
     * Die Instanz der {@link Database Datenbank} wird zugewiesen.
     */
    public Database() {
        instance = this;
    }

    /**
     * Wenn noch keine Verbindung zur Datenbank besteht, wird eine Verbindung hergestellt und ansonsten wird eine
     * Fehlermeldung ausgegeben und {@code disconnect} die Verbindung getrennt bevor sie sich wieder neu verbindet.
     */
    @SneakyThrows
    public void connect() {
        if (isConnected()) {
            System.out.println("Database is already connected!");
            System.out.println("Diconnecting...");
            disconnect();
            System.out.println("Diconnected!");
        }
        connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE, USER, PASSWORD);
        statement = getInstance().connection.createStatement();
        System.out.println("Connected!");
    }

    /**
     * Wenn eine Verbindung zur Datenbank besteht, wird diese getrennt und ansonsten wird eine Fehlermeldung
     * ausgegeben.
     */
    @SneakyThrows
    public void disconnect() {
        if (!isConnected()) {
            System.out.println("Database is already disconnected!");
            return;
        }
        connection.close();
        System.out.println("Disconnected!");
    }

    /**
     * Es wird geprüft, ob eine Verbindung zur Datenbank besteht.
     *
     * @return Besteht eine Verbindung oder nicht.
     */
    public boolean isConnected() {
        return connection != null;
    }

    /**
     * Es wird überprüft, ob es eine bestimmte Tabelle in der Datenbank gibt.
     *
     * @param table Der Name der Tabelle.
     *
     * @return Gibt es diese Tabelle oder nicht.
     */
    @SneakyThrows
    public boolean isCreated(@NotNull final String table) {
        return Database.getInstance().connection.getMetaData().getTables(null, null, table, null).next();
    }

    /**
     * Es wird eine neue Tabelle in der Datenbank erstellt.
     *
     * @param name      Der Name der Tabelle, die erstellt wird.
     * @param arguments Die Argumente (Spalten), die für das erstellen der Tabelle benötigt wird bzw welche hinterher in
     *                  der Tabelle angezeigt werden und womit weitergearbeitet wird.
     */
    @SneakyThrows
    public void createTable(@NotNull final String name, @NotNull final String arguments) {
        statement.executeUpdate("CREATE TABLE " + name + "(" + arguments + ")");
    }

    /**
     * Fügt eine neue Zeile in einer bestimmten Tabelle hinzu.
     *
     * @param table  Der Name der Tabelle, in der die Werte hinzugefügt werden.
     * @param values Die Werte, die in die Zeile eingetragen werden.
     */
    @SneakyThrows
    public void insert(@NotNull final String table, @NotNull final String values) {
        statement.executeUpdate("INSERT INTO " + table + " VALUES(" + values + ")");
    }

    /**
     * Gibt die durch die angegebenen Argumente gefilterten Werte zurück.
     *
     * @param arguments Die Argumente, womit die Werte, die zurückgegeben werden sollen gefiltert, bzw ausgewählt
     *                  werde.
     *
     * @return Die entsprechenden Werte.
     */
    @SneakyThrows
    public ResultSet get(@NotNull final String arguments) {
        return Database.getInstance().statement.executeQuery(arguments);
    }

}
