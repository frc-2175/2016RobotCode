package org.usfirst.frc2175.config;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;

public class IntakeConfigTest extends TestBase {
    @Test
    public void testIntakeConfig_Competition()
            throws IllegalArgumentException, IllegalAccessException {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_COMPETITION);
        IntakeConfig sut = new IntakeConfig();
        assertDoublesNotZero(sut);
    }

    @Test
    public void testIntakeConfig_Practice()
            throws IllegalArgumentException, IllegalAccessException {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_PRACTICE);
        IntakeConfig sut = new IntakeConfig();
        assertDoublesNotZero(sut);
    }
}
