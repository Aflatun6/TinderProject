package dao;

import entity.User;

import java.util.List;
import java.util.Optional;

public class DAOuser implements DAO<User> {
    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Optional<User> get(String name) {
        return Optional.empty();
    }

    @Override
    public boolean delete(String name) {
        return false;
    }
}
