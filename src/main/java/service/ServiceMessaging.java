package service;

import dao.DAOuserSQL;

import java.sql.SQLException;

public class ServiceMessaging {
    DAOuserSQL dao  ;
    public ServiceMessaging() throws SQLException {
        dao = new DAOuserSQL();
    }
}
