package org.usfirst.frc2175.config;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc2175.TestBase;

public class BaseConfigTest extends TestBase {

    private static final String TEST_ARRAY_KEY = "key.array";

    private Properties props;
    private BaseConfig sut;

    @Before
    public void setUp() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_COMPETITION);
        sut = new BaseConfigImplementation();
        props = new Properties();
    }

    private void assertIntArrayMatches(int[] expected, int[] actual) {
        String lengthMsg = "Actual array had length " + actual.length
                + ", expected " + expected.length;
        assertEquals(lengthMsg, expected.length, actual.length);

        for (int i = 0; i < expected.length; i++) {
            String valueMsg = "Actual array had value " + actual[i]
                    + ", expected " + expected[i];
            assertEquals(valueMsg, expected[i], actual[i]);
        }
    }

    @Test
    public void testGetIntArrayPropertyValue() {
        int[] testArray = { 1, 2, 3 };

        props.setProperty(TEST_ARRAY_KEY, "[1, 2, 3]");
        int[] resultWithBrackets =
                sut.getIntArrayPropertyValue(TEST_ARRAY_KEY, props);
        assertIntArrayMatches(testArray, resultWithBrackets);

        props.setProperty(TEST_ARRAY_KEY, "1, 2, 3");
        int[] resultWithoutBrackets =
                sut.getIntArrayPropertyValue(TEST_ARRAY_KEY, props);
        assertIntArrayMatches(testArray, resultWithoutBrackets);

        props.setProperty(TEST_ARRAY_KEY, "[1,2,3]");
        int[] resultWithoutSpacesWithBrackets =
                sut.getIntArrayPropertyValue(TEST_ARRAY_KEY, props);
        assertIntArrayMatches(testArray, resultWithoutSpacesWithBrackets);

        props.setProperty(TEST_ARRAY_KEY, "1,2,3");
        int[] resultWithoutSpacesWithoutBrackets =
                sut.getIntArrayPropertyValue(TEST_ARRAY_KEY, props);
        assertIntArrayMatches(testArray, resultWithoutSpacesWithoutBrackets);

        int[] testArrayLonger = { 0, 2, 4, 6, 8, 10 };

        props.setProperty(TEST_ARRAY_KEY, "[0, 2, 4, 6, 8, 10]");
        int[] resultLonger =
                sut.getIntArrayPropertyValue(TEST_ARRAY_KEY, props);
        assertIntArrayMatches(testArrayLonger, resultLonger);
    }

    private class BaseConfigImplementation extends BaseConfig {
        @Override
        protected String getPropertyFileName() {
            return "gamepad.properties"; // Anything that works
        }

        @Override
        protected void configure(Properties properties) {
        }
    }

}
