package net.backdoorinc.community.mysql;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {
    public String username;

    public String password;

    public String database;

    public String host;

    public  String port;

    public Connection con;

    public void connect() {
        if (!isConnected())
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("MySQL-Verbindung konnte aufgrund eines Fehlers nicht hergestellt werden!");
            }
    }

    public void disconnect() {
        if (isConnected())
            try {
                con.close();
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("Schliesen MySQL-Verbindung ist ein Fehler aufgetreten!");
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
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Bans (Spielername VARCHAR(100), UUID VARCHAR(100), Ende VARCHAR(100), Grund VARCHAR(100), Dauer VARCHAR(100), Banner VARCHAR(100))");
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
