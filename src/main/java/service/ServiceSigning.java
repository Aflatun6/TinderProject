package service;

import dao.DAOuserSQL;
import entity.CurrentState;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ServiceSigning {
    DAOuserSQL dao;

    public ServiceSigning() throws SQLException {
        dao = new DAOuserSQL();
    }

    public void add(String name, String imageURL, String password) throws SQLException {
        dao.add(name, imageURL, password);
    }

    public boolean exist(String name, String password) throws SQLException {
        ResultSet rs = dao.exist(name, password);
        initiateUser(rs);
        return CurrentState.getCurrentUser() != null;
    }

    private void initiateUser(ResultSet rs) throws SQLException {
        if (rs.next()) {
            CurrentState.setCurrentUser(new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("imageurl"),
                    rs.getString("password")
            ));
        }
    }
}
