package org.usfirst.frc2175;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;
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
        processMockUps();
    }

    @After
    public void processTestFinished() {
        log.fine("Test finished: " + testName.getMethodName());
    }

    protected void processMockUps() {
        new MockJNIWrapper();
    }

    protected void assertInstanceVariablesNotNull(Object sut)
            throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = sut.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);

            if (!fields[i].getType().isPrimitive()) {
                String assertMessage =
                        "Field " + fields[i].getName() + " was null";
                assertNotNull(assertMessage, fields[i].get(sut));
            }
        }
    }

    protected void assertDoublesNotZero(Object sut)
            throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = sut.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);

            if (fields[i].getType().getName().equals("double")) {
                String assertMessage =
                        "Field " + fields[i].getName() + " was zero";
                assertNotEquals(assertMessage, 0, fields[i].get(sut));
            }
        }
    }
}
