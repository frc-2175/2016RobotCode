package org.usfirst.frc2175.config;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;

public class PowertrainConfigTest extends TestBase {
    @Test
    public void testPowertrainConfig_Competition()
            throws IllegalArgumentException, IllegalAccessException {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_COMPETITION);
        PowertrainConfig sut = new PowertrainConfig();
        assertInstanceVariablesNotNull(sut);
    }

    @Test
    public void testPowertrainConfig_Practice()
            throws IllegalArgumentException, IllegalAccessException {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_PRACTICE);
        PowertrainConfig sut = new PowertrainConfig();
        assertInstanceVariablesNotNull(sut);
    }
}
