package db;

import java.sql.*;

public class DBApp {

    private static final String NAME = "slwfzdunpgpvgz";
    private static final String PASSWORD = "cf9a4a0e54c459192e02bd52e65896245e749fd9d939065dc1a96ecf056042fe";
    private static final String URL = "jdbc:postgresql://ec2-54-228-251-117.eu-west-1.compute.amazonaws.com:5432/d1h69thpfb2nha";
    private static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet take(String sql) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(sql);
        return ps.executeQuery();
    }


    public static void update(String sql) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.execute();
    }


}
