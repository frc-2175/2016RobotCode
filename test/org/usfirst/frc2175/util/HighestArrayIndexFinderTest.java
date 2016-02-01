package org.usfirst.frc2175.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;

public class HighestArrayIndexFinderTest extends TestBase {
    private HighestArrayIndexFinder sut = new HighestArrayIndexFinder();

    @Test
    public void testDetermineLargestArrayItemIndex() throws Exception {
        double[] array = { 123, 1235, 4, 167, 12, 1975, 14 };
        int expectedValue = 5;

        int actualValue = sut.determineLargestArrayItemIndex(array);

        assertEquals("Index does not match!", expectedValue, actualValue);
    }

    @Test
    public void testDetermineLargestArrayItemIndex_EmptyArray() {
        double[] array = {};
        int expectedValue = -1;

        int actualValue = sut.determineLargestArrayItemIndex(array);

        assertEquals("Index does not match!", expectedValue, actualValue);
    }
}
