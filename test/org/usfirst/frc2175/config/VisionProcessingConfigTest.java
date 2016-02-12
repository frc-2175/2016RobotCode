package org.usfirst.frc2175.config;

import mockit.Mocked;

import org.junit.Ignore;
import org.junit.Test;
import org.usfirst.frc2175.TestBase;
import org.usfirst.frc2175.util.HighestArrayIndexFinder;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionProcessingConfigTest extends TestBase {
    @Mocked
    private NetworkTable networkTable;
    @Mocked
    private HighestArrayIndexFinder highestArrayIndexFinder;

    @Ignore("Do not know if it works or not.")
    @Test
    @SuppressWarnings("unused")
    public void testVisionProcessingConfig_Competition() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_COMPETITION);
        VisionProcessingConfig sut = new VisionProcessingConfig();
    }

    @Ignore("Do not know if it works or not.")
    @Test
    @SuppressWarnings("unused")
    public void testVisionProcessingConfig_Practice() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_PRACTICE);
        VisionProcessingConfig sut = new VisionProcessingConfig();
    }
}
