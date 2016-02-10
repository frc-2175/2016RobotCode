package org.usfirst.frc2175.util;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;
import org.usfirst.frc2175.config.BaseConfig;
import org.usfirst.frc2175.config.ControlLoopConfig;

public class ControlLoopConfigTest extends TestBase {
    @Test
    public void testControlLoopConfig_Competition() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_COMPETITION);
        ControlLoopConfig sut = new ControlLoopConfig();
    }

    @Test
    public void testControlLoopConfig_Practice() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_PRACTICE);
        ControlLoopConfig sut = new ControlLoopConfig();
    }
}
