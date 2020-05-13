package service;

import dao.DAOlikeduser;
import dao.DAOuser;
import entity.User;

import java.util.List;


public class Service {
    private final DAOuser daOuser = new DAOuser();
    private final DAOlikeduser daOlikeduser = new DAOlikeduser();

    public List<User> getAll() {
        return daOuser.getAll();
    }

    public User get(String name) {
        return daOuser.get(name);
    }

    public boolean delete(User user) {
        return daOuser.delete(user);
    }

    public boolean add(String name, String imageURL) {
        User user = new User(name, imageURL);
        return daOuser.add(user);
    }

    public User findFirst() {
        if (daOuser.getAll().size() > 0)
            return daOuser.getAll().get(0);
        return null;
    }

    public boolean addLikedUser(User user){
        return daOlikeduser.add(user);
    }

}
