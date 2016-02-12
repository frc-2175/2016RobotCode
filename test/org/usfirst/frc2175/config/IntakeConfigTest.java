package org.usfirst.frc2175.config;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;

public class IntakeConfigTest extends TestBase {
    @Test
    @SuppressWarnings("unused")
    public void testIntakeConfig_Competition() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_COMPETITION);
        IntakeConfig sut = new IntakeConfig();
    }

    @Test
    @SuppressWarnings("unused")
    public void testIntakeConfig_Practice() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_PRACTICE);
        IntakeConfig sut = new IntakeConfig();
    }
}
