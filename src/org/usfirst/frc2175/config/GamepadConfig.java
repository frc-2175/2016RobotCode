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

    private JoystickButton extendCatapultFullPower;
    private JoystickButton extendCatapultMiddleOn;
    private JoystickButton extendCatapultSidesOnly;
    private JoystickButton retractCatapult;
    private JoystickButton runIntakeIn;
    private JoystickButton runIntakeOut;
    private JoystickButton testAction;
    private JoystickButton lowerBoot;
    private JoystickButton raiseBoot;
    private JoystickButton lowerIntake;
    private JoystickButton raiseIntake;

    @Override
    public String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        gamepad = new Joystick(getIntPropertyValue("gamepad.port", properties));

        extendCatapultFullPower = new JoystickButton(gamepad,
                getIntPropertyValue("button.catapult.extend.full", properties));
        extendCatapultMiddleOn =
                new JoystickButton(gamepad, getIntPropertyValue(
                        "button.catapult.extend.middleOn", properties));
        extendCatapultSidesOnly =
                new JoystickButton(gamepad, getIntPropertyValue(
                        "button.catapult.extend.sidesOnly", properties));
        retractCatapult = new JoystickButton(gamepad,
                getIntPropertyValue("button.catapult.retract", properties));
        runIntakeIn = new JoystickButton(gamepad,
                getIntPropertyValue("button.intake.in", properties));
        runIntakeOut = new JoystickButton(gamepad,
                getIntPropertyValue("button.intake.out", properties));
        testAction = new JoystickButton(gamepad,
                getIntPropertyValue("button.action.test", properties));
        lowerBoot = new JoystickButton(gamepad,
                getIntPropertyValue("button.boot.lower", properties));
        raiseBoot = new JoystickButton(gamepad,
                getIntPropertyValue("button.boot.raise", properties));
        raiseIntake = new JoystickButton(gamepad,
                getIntPropertyValue("button.intake.raise", properties));
        lowerIntake = new JoystickButton(gamepad,
                getIntPropertyValue("button.intake.lower", properties));
    }

    public JoystickButton getLowerIntake() {
        return lowerIntake;
    }

    public JoystickButton getRaiseIntake() {
        return raiseIntake;
    }

    public JoystickButton getRetractCatapult() {
        return retractCatapult;
    }

    public JoystickButton getRunIntakeIn() {
        return runIntakeIn;
    }

    public JoystickButton getRunIntakeOut() {
        return runIntakeOut;
    }

    public JoystickButton getTestAction() {
        return testAction;
    }

    public JoystickButton getLowerBoot() {
        return lowerBoot;
    }

    public JoystickButton getRaiseBoot() {
        return raiseBoot;
    }

    public Joystick getGamepad() {
        return gamepad;
    }

    public JoystickButton getExtendCatapultFullPower() {
        return extendCatapultFullPower;
    }

    public JoystickButton getExtendCatapultMiddleOn() {
        return extendCatapultMiddleOn;
    }

    public JoystickButton getExtendCatapultSidesOnly() {
        return extendCatapultSidesOnly;
    }

}
