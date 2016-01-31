package org.usfirst.frc2175.config;

import java.util.Properties;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Gamepad configuration values - "weapons" controls.
 *
 * Set the values in the gamepad properties file.
 *
 * Do not use values directly in code, use these configuration methods.
 */
public class GamepadConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME = "gamepad.properties";

    private Joystick gamepad;

    private JoystickButton extendCatapult;
    private JoystickButton retractCatapult;
    private JoystickButton testAction;

    @Override
    public String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        gamepad = new Joystick(getIntPropertyValue("gamepad.port", properties));

        extendCatapult = new JoystickButton(gamepad, getIntPropertyValue("button.catapult.extend", properties));
        retractCatapult = new JoystickButton(gamepad, getIntPropertyValue("button.catapult.retract", properties));

        testAction = new JoystickButton(gamepad, getIntPropertyValue("button.action.test", properties));
    }

    public Joystick getGamepad() {
        return gamepad;
    }

    public JoystickButton getExtendCatapultButton() {
        return extendCatapult;
    }

    public JoystickButton getRetractCatapultButton() {
        return retractCatapult;
    }

    public JoystickButton getTestActionButton() {
        return testAction;
    }
}
