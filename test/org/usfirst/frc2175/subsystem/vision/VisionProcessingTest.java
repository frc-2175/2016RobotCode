package org.usfirst.frc2175.subsystem.vision;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.util.HighestArrayIndexFinder;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;

public class VisionProcessingTest extends TestBase {
    @Mocked
    private RobotConfig robotConfig;

    @Mocked
    private NetworkTable networkTable;

    @Test
    public void testGetLargestContourCenterX_NoElements_NO_VALUES() {
        double expected = HighestArrayIndexFinder.NO_VALUES;

        final VisionProcessing sut = new VisionProcessing(robotConfig);
        double actual = sut.getLargestContourCenterX();

        assertThat("NO_VALUES center X value not found.", actual,
                equalTo(expected));
    }

    @Test
    public void testGetLargestContourCenterX_SameElementCount_NewElementValue() {
        new Expectations() {
            {
                double[] centers = { 4, 6, 8, 2 };
                networkTable.getNumberArray("centerX",
                        VisionProcessing.DEFAULT_NUMBER_ARRAY);
                result = centers;

                double[] widths = { 4, 6, 8, 2 };
                networkTable.getNumberArray("width",
                        VisionProcessing.DEFAULT_NUMBER_ARRAY);
                result = widths;
            }
        };

        double expected = 8;

        final VisionProcessing sut = new VisionProcessing(robotConfig);
        double actual = sut.getLargestContourCenterX();

        assertThat("Wrong center X value found.", actual, equalTo(expected));
    }

    @Test
    public void testGetLargestContourCenterX_NotSameElementCount_PreviousValue() {
        new Expectations() {
            {
                double[] centers = { 4, 6 };
                networkTable.getNumberArray("centerX",
                        VisionProcessing.DEFAULT_NUMBER_ARRAY);
                result = centers;

                double[] widths = { 4, 6, 8, 2 };
                networkTable.getNumberArray("width",
                        VisionProcessing.DEFAULT_NUMBER_ARRAY);
                result = widths;
            }
        };

        double expected = 10;

        final VisionProcessing sut = new VisionProcessing(robotConfig);
        Deencapsulation.setField(sut, "previousCenterXValue", expected);

        double actual = sut.getLargestContourCenterX();

        assertThat("Previous center X value not found.", actual,
                equalTo(expected));
    }
}
