package service;


import dao.DAOuserSQL;
import entity.User;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ServiceUsers {
    private static User currentUser;
    private DAOuserSQL dao;
    private List<User> users;
    private List<User> likedUsers;
    private static List<User> temporaryList;

    public ServiceUsers() throws SQLException {
        dao = new DAOuserSQL();
        users = new ArrayList<>();
        likedUsers = new ArrayList<>();
        temporaryList = new ArrayList<>();
    }

    public void getAll() throws SQLException {
        initiateUsers(dao.getAll());
        doMath();
    }

    private static void initiateCurrentUser() throws SQLException {
        currentUser = ServiceSigning.getCurrentUser();
    }

    private void doMath() throws SQLException {
        users.removeAll(likedUsers);
        users.remove(currentUser);
    }

    public List<User> getLiked(){
        return likedUsers;
    }

    public void getLikedIds() throws SQLException {
        ResultSet likedIDs = dao.getLiked(currentUser.getId());
        while (likedIDs.next()) {
            likedUsers.add(initiateLikedUsers(dao.get(likedIDs.getInt("whom"))));
        }
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

    private void initiateUsers(ResultSet rs) throws SQLException {
        while (rs.next()) {
            users.add(new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("imageurl"),
                    rs.getString("password")
            ));
        }
    }

    public User findFirst() throws SQLException {
        initiateCurrentUser();
        getLikedIds();
        getAll();
        if (users.size() > 0) {
            return users.remove(0);
        }
        return null;
    }

    public static void addTemp(User user) {
        temporaryList.add(user);
    }

    public void fillUsers() {
        if (users.isEmpty()) {
            System.out.println(temporaryList.size() + " = temp.size");
            users.addAll(temporaryList);
        }
        temporaryList.clear();
    }

    public void addLiked(User user) throws SQLException {
        dao.addLiked(currentUser.getId(), user.getId());
    }
}
