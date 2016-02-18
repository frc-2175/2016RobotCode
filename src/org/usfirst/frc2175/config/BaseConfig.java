package org.usfirst.frc2175.config;

import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Base class for configuration from loading values from properties file.
 */
public abstract class BaseConfig {
    private final Logger log = Logger.getLogger(getClass().getName());

    private static final String PROPERTY_FILE_DIR_DEFAULT = "/home/lvuser/";

    private static String propertyFileDir = PROPERTY_FILE_DIR_DEFAULT;

    public BaseConfig() {
        final String propertyFileName = propertyFileDir + getPropertyFileName();
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

    protected int[] getIntArrayPropertyValue(String propertyName,
            Properties props) {
        // TODO Really really should have a unit test

        String rawValues = props.getProperty(propertyName);
        ArrayList<Integer> values = new ArrayList<Integer>();

        rawValues = rawValues.replace("[", "");
        rawValues = rawValues.replace("]", "");
        final String[] splitValues = rawValues.split(",");

        int length = splitValues.length;

        for (int i = 0; i < length; i++) {
            values.add(Integer.parseInt(splitValues[i]));
        }

        int[] returnValues = new int[values.size()];

        for (int i = 0; i < values.size(); i++) {
            returnValues[i] = values.get(i);
        }

        return returnValues;

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

    protected boolean getBooleanPropertyValue(String propertyName,
            Properties props) {
        final String propertyValue =
                getStringPropertyValue(propertyName, props);
        return Boolean.parseBoolean(propertyValue);
    }

    public static String getPropertyFileDir() {
        return propertyFileDir;
    }

    public static void setPropertyFileDir(String propertyFileDirectory) {
        propertyFileDir = propertyFileDirectory;
    }
}
