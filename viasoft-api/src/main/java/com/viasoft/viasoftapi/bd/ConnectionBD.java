package com.viasoft.viasoftapi.bd;


import java.sql.*;

public class ConnectionBD {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/bd?autoReconnect=true&useSSL=false";
    private static final String username = "root";
    private static final String password = "1234";

    public static Connection getConnection() {
        try {
            Class.forName(driver);

            return DriverManager.getConnection(url, username, password);
        } catch ( ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro connection " + e);
        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);
        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
