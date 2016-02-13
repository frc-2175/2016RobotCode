package org.usfirst.frc2175.config;

import mockit.Mocked;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class GamepadConfigTest extends TestBase {
    @Mocked
    private Joystick joystick;

    @Mocked
    private JoystickButton joystickButton;

    @Test
    @SuppressWarnings("unused")
    public void testGamepadConfig_Competition() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_COMPETITION);
        GamepadConfig sut = new GamepadConfig();
    }

    @Test
    @SuppressWarnings("unused")
    public void testGamepadConfig_Practice() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_PRACTICE);
        GamepadConfig sut = new GamepadConfig();
    }
}
