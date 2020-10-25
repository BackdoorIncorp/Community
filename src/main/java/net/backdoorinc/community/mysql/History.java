package net.backdoorinc.community.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class History {
    public String username;

    public String password;

    public String database;

    public String host;

    public String port;

    public Connection con;

    public void connect() {
        if (!isConnected())
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
            } catch (SQLException e) {
            }
    }

    public void disconnect() {
        if (isConnected())
            try {
                con.close();
            } catch (SQLException e) {
            }
    }

    public boolean isConnected() {
        return (con != null);
    }

    public void update(String qry) {
        if (isConnected())
            try {
                con.createStatement().executeUpdate(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void createTable() {
        if (isConnected())
            try {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS History (Typ VARCHAR(100), UUID VARCHAR(100), Grund VARCHAR(100), Von VARCHAR(100), Dauer VARCHAR(100), Datum VARCHAR(100), Name VARCHAR(100))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public ResultSet getResult(String qry) {
        if (isConnected())
            try {
                return con.createStatement().executeQuery(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }
}
