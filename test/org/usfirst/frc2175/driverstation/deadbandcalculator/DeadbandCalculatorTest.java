package org.usfirst.frc2175.driverstation.deadbandcalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc2175.TestBase;
import org.usfirst.frc2175.driverstation.DeadbandCalculator;

public class DeadbandCalculatorTest extends TestBase {
    private DeadbandCalculator sut;

    @Before
    public void setUp() {
        sut = new DeadbandCalculator();
    }

    private void assertCorrectDeadbandOutput(double expected, double actual,
            double deadbandSize) {
        String msg = "Incorrect deadbanded output.";
        assertEquals(msg, expected,
                sut.calcDeadbandedOutput(actual, deadbandSize),
                ALLOWED_DOUBLE_DELTA);
    }

    @Test
    public void testCalcDeadbandedOutput() {
        for (double deadbandSize = 0; deadbandSize <= 0.8; deadbandSize += 0.2) {
            assertCorrectDeadbandOutput(0, 0, deadbandSize);
            assertCorrectDeadbandOutput(0, deadbandSize, deadbandSize);
            assertCorrectDeadbandOutput(0, -deadbandSize, deadbandSize);
            assertCorrectDeadbandOutput(1, 1, deadbandSize);
            assertCorrectDeadbandOutput(-1, -1, deadbandSize);

            double halfwayInput = deadbandSize + ((1 - deadbandSize) / 2);
            assertCorrectDeadbandOutput(0.5, halfwayInput, deadbandSize);
            assertCorrectDeadbandOutput(-0.5, -halfwayInput, deadbandSize);
        }
    }

    private void assertCorrectLineSlope(double shiftSize, double expected) {
        double actual = sut.calcLinearOutputSlope(shiftSize);

        String msg = "Incorrect slope for shift size " + shiftSize + ".";
        assertEquals(msg, expected, actual, ALLOWED_DOUBLE_DELTA);
    }

    @Test
    public void testCalcLinearOutputSlope() throws Exception {
        assertCorrectLineSlope(0, 1);
        assertCorrectLineSlope(0.5, 2);
    }

    @Test
    public void testIsAboveThreshold_Above() throws Exception {
        double value = 0.5;
        double threshold = 0.2;

        boolean actual = sut.isAboveThreshold(value, threshold);

        String msg =
                "Incorrectly returned that " + value + " was below " + threshold;
        assertTrue(msg, actual);
    }

    @Test
    public void testIsAboveThreshold_Below() throws Exception {
        double value = 0.18;
        double threshold = 0.2;

        boolean actual = sut.isAboveThreshold(value, threshold);

        String msg =
                "Incorrectly returned that " + value + " was above " + threshold;
        assertFalse(msg, actual);
    }

}
