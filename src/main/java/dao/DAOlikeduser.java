package dao;

import entity.User;

import java.util.ArrayList;
import java.util.List;


public class DAOlikeduser implements DAO<User> {
    private static List<User> users;

    public DAOlikeduser() {
        users = new ArrayList<>();
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User get(String name) {
//        return getAll().stream().filter(u -> u.getName().equals(name)).findFirst().orElse(new User(1,"Nobody left bro!", "https://robohash.org/68.186.255.198.png"));

   return null; }

    @Override
    public boolean add(User user) {
        return users.add(user);
    }

    @Override
    public boolean delete(User user) {
        return users.remove(user);
    }
}
