package org.usfirst.frc2175.config;

import java.util.Properties;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Joystick configuration values - "driving" controls.
 *
 * Set the values in the joysticks properties file.
 *
 * Do not use values directly in code, use these configuration methods.
 */
public class JoysticksConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME = "joysticks.properties";

    private Joystick leftStick;
    private Joystick rightStick;

    private JoystickButton upshift;

    private double deadbandValue;

    @Override
    public String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        int leftStickPort = getIntPropertyValue("leftStick.port", properties);
        leftStick = new Joystick(leftStickPort);

        int rightStickPort = getIntPropertyValue("rightStick.port", properties);
        rightStick = new Joystick(rightStickPort);

        deadbandValue = getDoublePropertyValue("deadband.value", properties);

        int upshiftButton = getIntPropertyValue("upshift.button", properties);
        upshift = new JoystickButton(leftStick, upshiftButton);
    }

    public Joystick getLeftStick() {
        return leftStick;
    }

    public Joystick getRightStick() {
        return rightStick;
    }

    public double getDeadbandSize() {
        return deadbandValue;
    }

    public JoystickButton getUpshiftButton() {
        return upshift;
    }
}
