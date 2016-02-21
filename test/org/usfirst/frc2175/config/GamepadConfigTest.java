package org.usfirst.frc2175.config;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import mockit.Mocked;

public class GamepadConfigTest extends TestBase {
    @Mocked
    private Joystick joystick;

    @Mocked
    private JoystickButton joystickButton;

    @Test
    public void testGamepadConfig_Competition()
            throws IllegalArgumentException, IllegalAccessException {
        String propertyFileDirectory = PROPERTY_FILE_DIR_SRC_COMPETITION;
        commonTestConfig(propertyFileDirectory);
    }

    @Test
    public void testGamepadConfig_Practice()
            throws IllegalArgumentException, IllegalAccessException {
        String propertyFileDirectory = PROPERTY_FILE_DIR_SRC_PRACTICE;
        commonTestConfig(propertyFileDirectory);
    }

    private void commonTestConfig(String propertyFileDirectory)
            throws IllegalArgumentException, IllegalAccessException {
        BaseConfig.setPropertyFileDir(propertyFileDirectory);
        BaseConfig baseConfig = new GamepadConfig();
        assertInstanceVariablesNotNull(baseConfig);
    }
    }
}
