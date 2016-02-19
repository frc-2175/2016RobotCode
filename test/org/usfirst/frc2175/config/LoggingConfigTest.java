package org.usfirst.frc2175.config;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;

public class LoggingConfigTest extends TestBase {
    /**
     * Verifies can find and read the logging config properties file and
     * displays example logging output.
     */
    @Test
    public void testInitializeFileLog() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_COMPETITION);
        final LoggingConfiguration sut = new LoggingConfiguration();
        final String propertyFile = sut.getFullyQualifiedPropertyFileName();

        sut.initializeFileLog(propertyFile);

        final Logger log = Logger.getLogger(getClass().getName());

        final Exception e = new IllegalArgumentException("the exception msg");
        log.log(Level.SEVERE, "Exception msg", e);

        log.info("log an info msg");
        log.config("log a config msg");
        log.fine("log a fine msg");
        log.finer("log a finer msg");
        log.finest("log a finest msg");
    }

    @Test
    public void testInitializeSocketLog() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_COMPETITION);
        final LoggingConfiguration sut = new LoggingConfiguration();
        final String propertyFile = sut.getFullyQualifiedPropertyFileName();

        sut.initializeFileLog(propertyFile);

        final Logger log = Logger.getLogger(getClass().getName());
        log.info("log a test msg");
    }
}