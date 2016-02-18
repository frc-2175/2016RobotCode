package org.usfirst.frc2175.config;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

public class LoggingConfigTest {
    final LoggingConfiguration sut = new LoggingConfiguration();

    /**
     * Verifies can find and read the logging config properties file and
     * displays example logging output.
     */
    @Test
    public void testInitializeFileLog() {
        sut.setLoggingPropertiesFileToUse(
                LoggingConfiguration.LOGGING_PROPERTIES_FILE__LOCATION);
        sut.initializeFileLog();

        Logger log = Logger.getLogger(getClass().getName());

        Exception e = new IllegalArgumentException("the exception msg");
        log.log(Level.SEVERE, "Exception msg", e);

        log.info("log an info msg");
        log.config("log a config msg");
        log.fine("log a fine msg");
        log.finer("log a finer msg");
        log.finest("log a finest msg");
    }

    @Test
    public void testInitializeSocketLog() {
        sut.setLoggingPropertiesFileToUse(
                LoggingConfiguration.LOGGING_PROPERTIES_FILE__LOCATION);
        sut.initializeSocketLog();
        Logger log = Logger.getLogger(getClass().getName());
        log.info("log a test msg");
    }

    @Test
    public void testInitializeLogging() {
        sut.setLoggingPropertiesFileToUse(
                LoggingConfiguration.LOGGING_PROPERTIES_FILE__LOCATION);
        sut.initializeLogging();

        Logger log = Logger.getLogger(getClass().getName());
        log.info("log a test msg");
    }
}