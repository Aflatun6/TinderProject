package dao;


import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DAOuserSQL {

    private static final String NAME = "slwfzdunpgpvgz";
    private static final String PASSWORD = "cf9a4a0e54c459192e02bd52e65896245e749fd9d939065dc1a96ecf056042fe";
    private static final String URL = "jdbc:postgresql://ec2-54-228-251-117.eu-west-1.compute.amazonaws.com:5432/d1h69thpfb2nha";
    private static Connection conn;

    List<User> users;
    List<User> likedUsers;

    public DAOuserSQL() throws SQLException {
        conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        users = new ArrayList<>();
        likedUsers = new ArrayList<>();
    }

    public ResultSet get(int id) throws SQLException {
        String sql = "select * from users where id = ?";
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setInt(1, id);
        return pr.executeQuery();
    }

    public ResultSet getAll() throws SQLException {
        String sql = "select * from users";
        PreparedStatement pr = conn.prepareStatement(sql);
        return pr.executeQuery();
    }

    public ResultSet getLiked(int id) throws SQLException {
        String sql = "select whom from likes where who = ?";
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setInt(1, id);
        return pr.executeQuery();
    }

    public void add(String name, String imageURL, String password) throws SQLException {
        String sql = "insert into public.users (id,name,imageurl,password) values (default,?, ?, ?)";
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setString(1, name);
        pr.setString(2, imageURL);
        pr.setString(3, password);
        pr.executeUpdate();
    }

    public ResultSet exist(String name, String password) throws SQLException {
        String sql = "select * from users u where name = ? and password = ?";
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setString(1, name);
        pr.setString(2, password);
        return pr.executeQuery();
    }

    public void addLiked(int who, int whom) throws SQLException {
        String sql = "insert into likes (who,whom) values (?,?)";
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setInt(1, who);
        pr.setInt(2, whom);
        pr.executeUpdate();
    }
}