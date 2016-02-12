package org.usfirst.frc2175.config;

import org.junit.Ignore;
import org.junit.Test;
import org.usfirst.frc2175.TestBase;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import mockit.Mocked;

public class JoysticksConfigTest extends TestBase {
    @Mocked
    private Joystick joystick;

    @Mocked
    private JoystickButton joystickButton;

    // Additional Mocking? Won't work at the moment.
    @Ignore
    @Test
    public void testJoystickConfig_Competition() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_COMPETITION);
        JoysticksConfig sut = new JoysticksConfig();
    }

    @Ignore
    @Test
    public void testJoystickConfig_Practice() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_PRACTICE);
        JoysticksConfig sut = new JoysticksConfig();
    }
}
