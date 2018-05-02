/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Tietokanta
 */
public class Database {
    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
        this.init();
    }

    public Connection getConnection() throws SQLException {
        String dbUrl = System.getenv("JBDC_DATABASE_URL");
        if (dbUrl != null && dbUrl.length() > 0) {
            return DriverManager.getConnection(dbUrl);
        }
        return DriverManager.getConnection(databaseAddress);
    }
    
    public void init() {
        List<String> commands = this.sqliteCommands();
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            for (String com : commands) {
                System.out.println("Running command: " + com);
                st.executeUpdate(com);
            }
        } catch (Throwable t) {
            System.out.println("Error: " + t.getMessage());
        }
    }
    
    private List<String> sqliteCommands() {
        ArrayList<String> list = new ArrayList<>();
        list.add("CREATE TABLE Pelaaja (id integer PRIMARY KEY, nimi varchar(50), rahaa integer);");
        list.add("INSERT INTO Pelaaja (nimi, rahaa) VALUES ('Henri', 99999990);");
        return list;
    }
    
}