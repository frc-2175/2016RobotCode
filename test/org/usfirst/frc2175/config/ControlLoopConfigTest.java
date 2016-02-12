package org.usfirst.frc2175.config;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;

public class ControlLoopConfigTest extends TestBase {
    @Test
    @SuppressWarnings("unused")
    public void testControlLoopConfig_Competition() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_COMPETITION);
        ControlLoopConfig sut = new ControlLoopConfig();
    }

    @Test
    @SuppressWarnings("unused")
    public void testControlLoopConfig_Practice() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_PRACTICE);
        ControlLoopConfig sut = new ControlLoopConfig();
    }
}
