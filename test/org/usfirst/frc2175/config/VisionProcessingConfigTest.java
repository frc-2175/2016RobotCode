package org.usfirst.frc2175.config;

import mockit.Mocked;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;
import org.usfirst.frc2175.util.HighestArrayIndexFinder;

import edu.wpi.first.wpilibj.HLUsageReporting;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionProcessingConfigTest extends TestBase {
    @Mocked
    private HLUsageReporting hlUsageReporting;
    @Mocked
    private NetworkTable networkTable;
    @Mocked
    private HighestArrayIndexFinder highestArrayIndexFinder;

    @Test
    @SuppressWarnings("unused")
    public void testVisionProcessingConfig_Competition() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_COMPETITION);
        VisionProcessingConfig sut = new VisionProcessingConfig();
    }

    @Test
    @SuppressWarnings("unused")
    public void testVisionProcessingConfig_Practice() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_PRACTICE);
        VisionProcessingConfig sut = new VisionProcessingConfig();
    }
}
