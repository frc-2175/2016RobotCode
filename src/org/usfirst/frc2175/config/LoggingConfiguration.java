package org.usfirst.frc2175.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SocketHandler;
import java.util.logging.XMLFormatter;

public class LoggingConfiguration extends BaseConfig {
    /** Use ROBOT_LOCATION for deployment running. */
    public static final String LOGGING_PROPERTIES_FILE_ROBOT_LOCATION =
            "/home/lvuser/logging.properties";

    /** Use ACTUAL_LOCATION for tests. */
    public static final String LOGGING_PROPERTIES_FILE_ACTUAL_LOCATION =
            "src/properties/competitionbot/logging.properties";

    private String loggingPropertiesFileToUse =
            LOGGING_PROPERTIES_FILE_ROBOT_LOCATION;

    public void initializeLogging() {
        initializeFileLog();
        initializeSocketLog();
    }

    protected void initializeFileLog() {
        final LogManager logManager = LogManager.getLogManager();

        try (InputStream in =
                new FileInputStream(loggingPropertiesFileToUse);) {
            logManager.readConfiguration(in);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(
                    "Did not find logging properties file="
                            + loggingPropertiesFileToUse + ", msg="
                            + e.getMessage(),
                    e);
        } catch (SecurityException | IOException e) {
            throw new IllegalStateException("Unable to read logging properties",
                    e);
        }

        final String levelProperty =
                logManager.getProperty("java.util.logging.FileHandler.level");

        final Logger log = Logger.getLogger(getClass().getName());
        final Level level = log.getLevel();
        log.info("File logging initialized, actual logging level=" + level
                + ", configured level=" + levelProperty);
    }

    protected void initializeSocketLog() {
        final Handler handler = makeSocketHandler();

        if (handler != null) {
            configureSocketHandler(handler);
        }
    }

    protected Handler makeSocketHandler() {
        final Logger log = Logger.getLogger(getClass().getName());

        final Properties props = new PropertiesLoader()
                .loadProperties(loggingPropertiesFileToUse);

        final String socketHandlerHostname =
                getStringPropertyValue("socket.handler.hostname", props);
        final int socketHandlerPort =
                getIntPropertyValue("socket.handler.port", props);

        log.config("host=" + socketHandlerHostname + ", port="
                + socketHandlerPort);

        Handler handler = null;
        try {
            handler =
                    new SocketHandler(socketHandlerHostname, socketHandlerPort);
        } catch (IOException e) {
            final String msg = "Lilith log viewer not running?"
                    + " Error instantiating SocketHandler with host="
                    + socketHandlerHostname + ", port=" + socketHandlerPort
                    + ", msg=" + e.getClass().getName() + ": " + e.getMessage();
            log.info(msg);
        }
        return handler;
    }

    protected void configureSocketHandler(final Handler handler) {
        final String handlerEncoding = "UTF-8";
        try {
            handler.setEncoding(handlerEncoding);
        } catch (SecurityException | UnsupportedEncodingException e) {
            throw new IllegalStateException(
                    "Error setting handler encoding=" + handlerEncoding, e);
        }

        final Formatter socketHandlerFormatter = new XMLFormatter();
        handler.setFormatter(socketHandlerFormatter);

        final Logger rootLogger = Logger.getLogger("");
        rootLogger.addHandler(handler);
    }

    public String getLoggingPropertiesFileToUse() {
        return loggingPropertiesFileToUse;
    }

    public void setLoggingPropertiesFileToUse(
            String loggingPropertiesFileToUse) {
        this.loggingPropertiesFileToUse = loggingPropertiesFileToUse;
    }

    @Override
    protected String getPropertyFileName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void configure(Properties properties) {
        // TODO Auto-generated method stub

    }

}