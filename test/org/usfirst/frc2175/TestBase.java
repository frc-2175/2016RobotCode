package org.usfirst.frc2175;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

/**
 * Base test class for use by all tests.
 *
 * @author jjensen
 */
public abstract class TestBase {
    private final Logger log = Logger.getLogger(getClass().getName());

    public static final String PROPERTY_FILE_DIR_SRC_COMPETITION =
            "src/properties/competitionbot/";
    public static final String PROPERTY_FILE_DIR_SRC_PRACTICE =
            "src/properties/practicebot/";
    @Rule
    public TestName testName = new TestName();

    @Before
    public void processTestStarting() {
        log.fine("Test starting: " + testName.getMethodName());
    }

    @After
    public void processTestFinished() {
        log.fine("Test finished: " + testName.getMethodName());
    }
}
