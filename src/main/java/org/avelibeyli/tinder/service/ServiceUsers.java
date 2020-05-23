package org.avelibeyli.tinder.service;


import org.avelibeyli.tinder.dao.DAOuserSQL;
import org.avelibeyli.tinder.entity.CurrentState;
import org.avelibeyli.tinder.entity.User;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ServiceUsers {
    private DAOuserSQL dao;
    private static int i = 0;

    public ServiceUsers() throws SQLException {
        dao = new DAOuserSQL();
    }

    public User get(int id) throws SQLException {
        ResultSet rs = dao.get(id);
        if (rs.next()) {
            return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("imageurl"),
                    rs.getString("password")
            );
        }
        return null; // Don't think there would be!
    }

    public void getAll() throws SQLException {
        CurrentState.setUsers(makeUsers(dao.getAll()));
        doMath();
    }

    private ArrayList<User> makeUsers(ResultSet rs) throws SQLException {
        ArrayList<User> aa = new ArrayList<>();
        while (rs.next()) {
            aa.add(new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("imageurl"),
                    rs.getString("password")
            ));
        }
        return aa;
    }

    private void doMath() {
        CurrentState.getUsers().removeAll(CurrentState.getLikedUsers());
        CurrentState.getUsers().remove(CurrentState.getCurrentUser());
    }

    public List<User> getLiked() throws SQLException {
        makeLiked();
        return CurrentState.getLikedUsers();
    }

    public void makeLiked() throws SQLException {
        ResultSet likedIDs = dao.getLiked(CurrentState.getCurrentUser().getId());
        List<User> aa = new ArrayList<>();
        while (likedIDs.next()) {
            aa.add(initiateLikedUsers(dao.get(likedIDs.getInt("whom"))));
        }
        CurrentState.setLikedUsers(aa);
    }

    private User initiateLikedUsers(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("imageurl"),
                    rs.getString("password")
            );
        }
        return null;
    }

    public void init() throws SQLException {
        makeLiked();
        getAll();
    }

    public User findFirst() throws SQLException {
        if (i != CurrentState.getCurrentUser().getId()) { // I DO IT JUST FOR TAKING USERS AND LIKED USERS  FROM DB  ONCE. AND WORK HERE WITH COLLECTIONS
            init();
            i = CurrentState.getCurrentUser().getId();
        }
        if (!CurrentState.getUsers().isEmpty()) {
            return CurrentState.getUsers().remove(0);
        }
        return null;
    }

    public void addAgain(User user) {
        CurrentState.getUsers().add(user);
    }

    public void addLiked(User user) throws SQLException {
        dao.addLiked(CurrentState.getCurrentUser().getId(), user.getId());
    }
}
