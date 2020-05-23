package org.avelibeyli.tinder.dao;


import java.sql.*;


public class DAOuserSQL {

    private final Connection conn;

    public DAOuserSQL() throws SQLException {
        String NAME = "slwfzdunpgpvgz";
        String PASSWORD = "cf9a4a0e54c459192e02bd52e65896245e749fd9d939065dc1a96ecf056042fe";
        String URL = "jdbc:postgresql://ec2-54-228-251-117.eu-west-1.compute.amazonaws.com:5432/d1h69thpfb2nha";
        conn = DriverManager.getConnection(URL, NAME, PASSWORD);
    }

    public ResultSet get(int id) throws SQLException {
        String sql = "select * from users where id = ?";
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setInt(1, id);
        pr.executeQuery();
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

    public void add(String name, String imageurl, String password) throws SQLException {
        String sql = "insert into public.users (id,name,imageurl,password) values (default,?, ?, ?)";
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setString(1, name);
        pr.setString(2, imageurl);
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

    public ResultSet getMessages(int who, int whom) throws SQLException {
        String sql = "select * from messages where who = ? and whom = ?";
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setInt(1, who);
        pr.setInt(2, whom);
        return pr.executeQuery();
    }

    public void addMessage(int who, int whom, String content) throws SQLException {
        String sql = "insert into public.messages(id, who, whom, content, date) values (default, ?, ?, ?, default)";
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setInt(1, who);
        pr.setInt(2, whom);
        pr.setString(3, content);
        pr.executeUpdate();
    }
}
