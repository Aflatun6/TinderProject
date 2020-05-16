package dao;

import db.DBApp;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DAOuserSQL {

    private static final String NAME = "slwfzdunpgpvgz";
    private static final String PASSWORD = "cf9a4a0e54c459192e02bd52e65896245e749fd9d939065dc1a96ecf056042fe";
    private static final String URL = "jdbc:postgresql://ec2-54-228-251-117.eu-west-1.compute.amazonaws.com:5432/d1h69thpfb2nha";
    private static Connection conn;

    private static List<User> users;

    public DAOuserSQL() throws SQLException {
        conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        users = new ArrayList<>();
    }

    //    @Override
//    public List<User> getAll() throws SQLException {
//        String sql = "select u.id,u.name, u.imageURL from users u";
//        ResultSet resultSet = DBApp.take(sql);
//        while (resultSet.next()) {
//            users.add(new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("imageURL")));
//        }
//        return users;
//    }


    //    @Override
    public void add(String name, String imageURL, String password) throws SQLException {
        String sql = "insert into public.users (id,name,imageurl,password) values (default,?, ?, ?)";
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setString(1, name);
        pr.setString(2, imageURL);
        pr.setString(3, password);
        pr.executeUpdate();
    }

    public boolean exist(String name) throws SQLException {
        String sql = "select u.name from users u where name = " + name;
        PreparedStatement pr = conn.prepareStatement(sql);
        ResultSet rs = pr.executeQuery();
        return rs.getFetchSize() == 1;
    }
}
