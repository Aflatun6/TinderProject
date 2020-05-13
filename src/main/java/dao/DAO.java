package dao;

import entity.User;

import java.util.List;


public interface DAO<A> {
    List<A> getAll();

    A get(String name);

    boolean add(User user);

    boolean delete(User user);
}
