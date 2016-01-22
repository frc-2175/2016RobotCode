package org.usfirst.frc2175.config;

import java.util.Properties;
import java.util.logging.Logger;

/**
 * Base class for configuration from loading values from properties file.
 */
public abstract class BaseConfig {
    private final Logger log = Logger.getLogger(getClass().getName());

    public BaseConfig() {
        final String propertyFileName = getPropertyFileName();
        final Properties properties =
                new PropertiesLoader().loadProperties(propertyFileName);
        configure(properties);
    }

    /** @return The property file name to load for this config. */
    protected abstract String getPropertyFileName();

    /**
     * @param properties
     *            The properties to use for configuration, loaded from the
     *            specified property file.
     */
    protected abstract void configure(Properties properties);

    protected String getStringPropertyValue(String propertyName,
            Properties props) {
        final String value = props.getProperty(propertyName);
        if (value == null) {
            String msg = "Property '" + propertyName
                    + "' not found in property file";
            log.severe(msg);
            throw new IllegalStateException(msg);
        }
        return value;
    }

    protected double getDoublePropertyValue(String propertyName,
            Properties props) {
        final String propertyValue =
                getStringPropertyValue(propertyName, props);
        return Double.parseDouble(propertyValue);
    }

    protected int getIntPropertyValue(String propertyName, Properties props) {
        final String propertyValue =
                getStringPropertyValue(propertyName, props);
        return Integer.parseInt(propertyValue);
    }
}
