package by.tms.project.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Class ConnectionPool
 *
 * @author ShchebetovaEK
 */
public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final int SIZE_POOL_CONNECTION = 7;
    private static final AtomicBoolean createInstance = new AtomicBoolean(false);
    private static final ReentrantLock lock = new ReentrantLock();
    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> usedConnections;
    private static ConnectionPool instance;

    private ConnectionPool() {
        freeConnections = new LinkedBlockingQueue<>(SIZE_POOL_CONNECTION);
        usedConnections = new LinkedBlockingQueue<>(SIZE_POOL_CONNECTION);
        init();
    }

    private void init() {

        try {
            for (int i = 0; i < SIZE_POOL_CONNECTION; i++) {
                ProxyConnection proxyConnection = new ProxyConnection(MySQLConnection.getConnection());
                freeConnections.offer(proxyConnection);

            }
        } catch (SQLException e) {
            throw new RuntimeException(" Connection don't create");
        }
    }

    /**
     * Get instance ConnectionPool
     *
     * @return the ConnectionPool
     */
    public static ConnectionPool getInstance() {
        if (!createInstance.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    createInstance.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * Get connection from ConnectionPool
     *
     * @return the connection
     */
    public Connection takeConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            logger.error("Problems with take connection {}", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    /**
     * Release connection.
     *
     * @param connection the connection
     * @return the boolean
     */
    public boolean releaseConnection(Connection connection) {
        if (!(connection instanceof ProxyConnection)) {
            return false;
        }
        try {
            ProxyConnection proxyConnection = (ProxyConnection) connection;
            usedConnections.remove(proxyConnection);
            freeConnections.put(proxyConnection);
        } catch (InterruptedException e) {
            logger.error("Have problems with release connection");
        }
        return true;
    }

    /**
     * Close Connection pool
     */
    public void closePool() {
        for (int i = 0; i < SIZE_POOL_CONNECTION; i++) {
            try {
                freeConnections.take().realClose();
            } catch (InterruptedException | SQLException e) {
                logger.error("Have problems with close connection pool {}", e);
            }
        }
        deregistredDriver();
    }

    private void deregistredDriver() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Have problem with deregister driver");
            }
        });
    }
}
