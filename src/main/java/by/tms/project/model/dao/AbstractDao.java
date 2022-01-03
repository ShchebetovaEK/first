package by.tms.project.model.dao;

import by.tms.project.model.connection.CustomConnectionPool;
import by.tms.project.model.entity.Entity;

import by.tms.project.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<K, T extends Entity> {
    private static final Logger logger = LogManager.getLogger();
    protected Connection connection;

    public AbstractDao() {
    }

    public abstract List<T> findAll() throws DaoException;

    public abstract Optional<T> findById(K id) throws DaoException;

    public abstract boolean create(T entity) throws DaoException;

    public abstract boolean update(T entity) throws DaoException;

    public abstract boolean delete(K entity) throws DaoException;

    public abstract boolean delete(T entity) throws DaoException;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    protected Connection getConnection() {
        return this.connection;
    }

    public void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error("Have problem with closing statement", e);
        }
    }

    void closeConnectionPool(Connection connection) {
        CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();
        connectionPool.releaseConnection(connection);
    }
}
