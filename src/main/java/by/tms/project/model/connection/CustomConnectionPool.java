package by.tms.project.model.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Class ConnectionPool
 *
 * @author ShchebetovaEK
 */
public final class CustomConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final int DEFAULT_POOL_SIZE = 32;
    private static final AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static final ReentrantLock lock = new ReentrantLock();
    private final BlockingQueue<ProxyConnection> freeConnections;
    private final BlockingQueue<ProxyConnection> usingConnections;
    private static CustomConnectionPool instance;

    private CustomConnectionPool(){
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        usingConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);

        try {
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                ProxyConnection connection = new ProxyConnection(CreateConnection.getConnection());
                freeConnections.offer(connection);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Connection is not create.", e);
        }
        if(freeConnections.isEmpty()){
            throw new RuntimeException("Connection pool is empty.");
        }
    }

    /**
     * Get instance CustomConnectionPool.
     *
     * @return the CustomConnectionPool
     */
    public static CustomConnectionPool getInstance(){
        if(!instanceCreated.get()){
            lock.lock();
            try{
                if(instance == null){
                    instance = new CustomConnectionPool();
                    instanceCreated.set(true);
                }
            }finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * Get connection from ConnectionPool.
     *
     * @return the connection
     */
    public Connection takeConnection(){
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            usingConnections.put(connection);
        } catch (InterruptedException e) {
            logger.error("There are some problems with get connection", e);
        }
        return connection;
    }

    /**
     * Release connection.
     *
     * @param connection the connection
     * @return the boolean
     */
    public boolean releaseConnection(Connection connection){
        if(!(connection instanceof ProxyConnection)) {
            return false;
        }
        try {
            ProxyConnection proxy = (ProxyConnection) connection;
            usingConnections.remove(proxy);
            freeConnections.put(proxy);
        } catch (InterruptedException e) {
            logger.error("There are some problems with release connection",e);
        }
        return true;
    }

    /**
     * Destroy ConnectionPool.
     */
    public void destroyPool(){
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().realClose();
            } catch (InterruptedException | SQLException e) {
                logger.error("There are some problems with destroying connection pool", e);
            }
        }
        deregisterDriver();
    }

    private void deregisterDriver(){
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("There are some problems with deregister driver ");
            }
        });
    }
}
