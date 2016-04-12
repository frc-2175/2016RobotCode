package org.usfirst.frc2175.config;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;

import edu.wpi.first.wpilibj.HLUsageReporting;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import mockit.Mocked;

public class VisionProcessingConfigTest extends TestBase {
    @Mocked
    private HLUsageReporting hlUsageReporting;
    @Mocked
    private NetworkTable networkTable;

    @Test
    public void testVisionProcessingConfig_Competition()
            throws IllegalArgumentException, IllegalAccessException {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_COMPETITION);
        VisionProcessingConfig sut = new VisionProcessingConfig();
        assertInstanceVariablesNotNull(sut);
    }

    @Test
    public void testVisionProcessingConfig_Practice()
            throws IllegalArgumentException, IllegalAccessException {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_PRACTICE);
        VisionProcessingConfig sut = new VisionProcessingConfig();
        assertInstanceVariablesNotNull(sut);
    }

    @Test
    public void testVisionProcessingArrayOutOfBoundsPatch() {
        VisionProcessingConfig visionProcessingConfig =
                new VisionProcessingConfig();
        visionProcessingConfig.getLargestContourCenterX();
    }

}
