package service;

import dao.DAOlikeduser;
import dao.DAOuser;
import entity.User;

import java.util.List;


public class ServiceLiked {
    private final DAOlikeduser daOlikeduser = new DAOlikeduser();

    public List<User> getAll() {
        return daOlikeduser.getAll();
    }



}
