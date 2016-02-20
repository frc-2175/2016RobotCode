package org.usfirst.frc2175.config;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;

public class ManipulatorConfigTest extends TestBase {
    @Test
    public void testJoystickConfig_Competition()
            throws IllegalArgumentException, IllegalAccessException {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_COMPETITION);
        ManipulatorConfig sut = new ManipulatorConfig();
        assertDoublesNotZero(sut);
    }
}
