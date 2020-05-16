package service;

import dao.DAOuserSQL;

import java.sql.SQLException;

public class ServiceSigning {
    DAOuserSQL dao;
    public ServiceSigning() throws SQLException {
        dao = new DAOuserSQL();
    }

    public void add(String name,String imageURL,String password) throws SQLException {
        dao.add(name,imageURL,password);
    }

    public boolean exist(String name){
        return dao.exist(name);
    }
}
