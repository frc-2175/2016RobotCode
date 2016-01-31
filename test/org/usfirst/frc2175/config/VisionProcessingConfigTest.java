package org.usfirst.frc2175.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VisionProcessingConfigTest {

    static {
        BaseConfig.setPropertyFileDir();
    }

    private VisionProcessingConfig sut = new VisionProcessingConfig();

    @Test
    public void testDetermineLargestArrayItemIndex() throws Exception {
        double[] inputArray = { 123, 11, 145, 124 };
        int expectedValue = 2;

        int actual = sut.determineLargestArrayItemIndex(inputArray);

        assertEquals("Incorrect array index calculated!", expectedValue, actual);
    }

}
