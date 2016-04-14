package org.usfirst.frc2175;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.usfirst.frc2175.config.BaseConfig;

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

    public final double ALLOWED_DOUBLE_DELTA = 0.01;

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
        new MockSmartDashboard();
        new MockNetworkTablesJNI();
    }

    protected void assertInstanceVariablesNotNull(Object sut)
            throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = sut.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            Class<?> type = field.getType();
            if (!type.isPrimitive()) {
                String assertMessage =
                        "Field " + field.getName() + " was null.";
                assertNotNull(assertMessage, field.get(sut));
            }
        }
    }

    protected void assertDoublesNotZero(Object sut)
            throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = sut.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            Class<?> type = field.getType();
            if (type.getName().equals("double")) {
                String assertMessage =
                        "Field " + field.getName() + " was zero.";
                assertNotEquals(assertMessage, 0.0, field.get(sut));
            }
        }
    }

    protected void assertArraysNotZeroLength(Object sut)
            throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = sut.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            Class<?> type = field.getType();
            if (type.isArray()) {
                Object array = field.get(sut);
                int length = Array.getLength(array);

                String assertMessage =
                        "Array field " + field.getName() + " was length zero.";
                assertNotEquals(assertMessage, 0, length);
            }
        }
    }

    protected void assertNoDuplicatePropertyValues(String propertyRegex,
            BaseConfig config) {
        final Properties properties = config.getProperties();
        final Set<String> propertyNames = properties.stringPropertyNames();

        long propertiesCount = propertyNames.size();
        assertFalse("No properties in config.", propertiesCount == 0);

        long matchedPropertiesCount = propertyNames.stream()
                .filter(key -> key.matches(propertyRegex)).count();
        long distinctValuesCount = propertyNames.stream()
                .filter(key -> key.matches(propertyRegex))
                .map(key -> properties.getProperty(key)).distinct().count();

        assertFalse("Zero property keys matched regex=" + propertyRegex,
                matchedPropertiesCount == 0);

        assertThat(
                "Probable duplicate value in property sequence with regex ='"
                        + propertyRegex + "'",
                distinctValuesCount, is(matchedPropertiesCount));
    }
}
