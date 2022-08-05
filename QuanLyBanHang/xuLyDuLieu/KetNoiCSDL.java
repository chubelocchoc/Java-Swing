/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xuLyDuLieu;


import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author 20520
 */
public class KetNoiCSDL {
    private String dbURL = "jdbc:sqlserver://localhost:1433;"
                + "DatabaseName=QLBH;"
                + "user=sa;"
                + "password=sa;";
    private Connection conn;
    private Statement stmt;

    public Connection getConn() {
        return conn;
    }

    public Statement getStmt() {
        return stmt;
    }

    public KetNoiCSDL() {
        try {
            conn = DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet getDuLieu(String query){
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public void setDuLieu(String query){
        try {
            stmt = conn.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                stmt.close();
            } catch (Exception e) {
            }
        }
    }
    
    public void offStatement(){
        try {
            stmt.close();
        } catch (Exception e) {
        }
    }
    
    public void offConnection(){
        try {
            conn.close();
        } catch (Exception e) {
        }
    }
}
