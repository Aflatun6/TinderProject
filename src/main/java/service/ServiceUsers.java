package service;


import dao.DAOuserSQL;
import entity.CurrentState;
import entity.User;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ServiceUsers {
    private CurrentState currentState ;
    private DAOuserSQL dao;
    private int i = 0;

    public ServiceUsers() throws SQLException {
        currentState = new CurrentState(1);
        dao = new DAOuserSQL();
    }

    public void getAll() throws SQLException {
        currentState.setUsers(makeUsers(dao.getAll()));
        doMath();
    }


    private void doMath() throws SQLException {
        currentState.getUsers().removeAll(currentState.getLikedUsers());
        currentState.getUsers().remove(currentState.getCurrentUser());
    }

    public List<User> getLiked() throws SQLException {
       return currentState.getLikedUsers();
    }

//    public Set<Integer> getLikedIds() throws SQLException {
//        ResultSet likedIDs = dao.getLiked(currentState.getCurrentUser().getId());
//        Set<Integer> set = new HashSet<>();
//        while(likedIDs.next()){
//            set.add(likedIDs.getInt("whom"));
//        }
//        return set;
//    }

    public void getLikedIds() throws SQLException {
        ResultSet likedIDs = dao.getLiked(currentState.getCurrentUser().getId());
        List<User> aa = new ArrayList<>();
        while (likedIDs.next()) {
            aa.add(initiateLikedUsers(dao.get(likedIDs.getInt("whom"))));
        }
        currentState.setLikedUsers(aa);
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

    private void init() throws SQLException {
        getLikedIds();
        getAll();
    }

    public User findFirst() throws SQLException {
        if(i==0){
            init();
        }
//        i++;
        if(!currentState.getUsers().isEmpty()){
            return currentState.getUsers().remove(0);
        }
        return null;
    }

    public void addAgain(User user) {
        currentState.getUsers().add(user);
    }

    public void addLiked(User user) throws SQLException {
        dao.addLiked(currentState.getCurrentUser().getId(), user.getId());
    }
}
