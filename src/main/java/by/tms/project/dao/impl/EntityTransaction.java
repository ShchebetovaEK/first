package by.tms.project.dao.impl;

import by.tms.project.connection.ConnectionPool;
import by.tms.project.dao.AbstractDao;
import by.tms.project.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction {
    private ConnectionPool connectionPool;
    private Connection connection;

    public EntityTransaction() {
    }

    public void beginTransaction(AbstractDao dao, AbstractDao... daos) throws DaoException {
        if (connection == null) {
            try {
                connectionPool = ConnectionPool.getInstance();
                connection = connectionPool.takeConnection();
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                throw  new DaoException("",e);
            }

    } dao.setConnection(connection);
}

public void begin(AbstractDao dao)throws DaoException{
        if(connection == null){
            try {
                connectionPool = ConnectionPool.getInstance();
                connection =connectionPool.takeConnection();
                connection.setAutoCommit(false);
            }catch (SQLException e){
                throw new DaoException("",e);
            }
        }
        dao.setConnection(connection);
}

    public void end()throws DaoException{
        try {
            connection.commit();
            connection.setAutoCommit(true);
        }catch (SQLException e){
            throw new DaoException("",e);
        }
        connectionPool.releaseConnection(connection);
        connection = null;
    }

    public void commit()throws DaoException{
        try{
            connection.commit();
        }catch (SQLException e){
            throw new DaoException("",e);

        }
    }
    public void rollback() throws DaoException{
        try {
            connection.rollback();
        }catch (SQLException e){
            throw new DaoException("",e);
        }
    }
}
