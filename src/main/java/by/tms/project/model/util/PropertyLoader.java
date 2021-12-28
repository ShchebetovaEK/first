package by.tms.project.model.util;

import by.tms.project.model.connection.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public final class PropertyLoader {
    private static final Logger logger = LogManager.getLogger();

    private PropertyLoader(){}


    public static Properties getProperty(String path) {
        Properties properties = new Properties();

        try {
            properties.load(CustomConnectionPool.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            logger.info("Getting properties has been failed.", e);
        }
        return properties;
    }
}