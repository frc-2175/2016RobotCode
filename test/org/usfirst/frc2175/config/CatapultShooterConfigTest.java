package org.usfirst.frc2175.config;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;

public class CatapultShooterConfigTest extends TestBase {
    @Test
    public void testJoystickConfig_Competition()
            throws IllegalArgumentException, IllegalAccessException {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_COMPETITION);
        CatapultShooterConfig sut = new CatapultShooterConfig();
        assertDoublesNotZero(sut);
    }

    @Test
    public void testJoystickConfig_Practice()
            throws IllegalArgumentException, IllegalAccessException {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_PRACTICE);
        CatapultShooterConfig sut = new CatapultShooterConfig();
        assertDoublesNotZero(sut);
    }
}
