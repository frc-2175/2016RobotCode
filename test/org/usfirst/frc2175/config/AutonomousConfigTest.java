package org.usfirst.frc2175.config;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;

public class AutonomousConfigTest extends TestBase {
    @Test
    public void testAutonomousConfig_Competition()
            throws IllegalArgumentException, IllegalAccessException {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_COMPETITION);
        AutonomousConfig sut = new AutonomousConfig();
        assertInstanceVariablesNotNull(sut);
    }

    @Test
    public void testAutonomousConfig_Practice()
            throws IllegalArgumentException, IllegalAccessException {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_PRACTICE);
        AutonomousConfig sut = new AutonomousConfig();
        assertInstanceVariablesNotNull(sut);
    }
}
