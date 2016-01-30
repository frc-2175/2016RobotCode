package org.usfirst.frc2175.config;

import java.util.Properties;

import edu.wpi.first.wpilibj.Joystick;

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

    private double driveValue;
    private double turnValue;

    @Override
    public String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        // TODO implement me
        // - add private instance variables for the values
        // - add get() methods for them

        int leftStickPort = getIntPropertyValue("leftStick.port", properties);
        leftStick = new Joystick(leftStickPort);

        int rightStickPort = getIntPropertyValue("rightStick.port", properties);
        rightStick = new Joystick(rightStickPort);

    }

    public int getLeftStickPort() {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getRightStickPort() {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getGamepadPort() {
        // TODO Auto-generated method stub
        return 0;
    }

    public double getDeadbandSize() {
        // TODO Load value from robot
        return 0;
    }

    public double getDriveValue() {
        return leftStick.getY();
    }

    public double getTurnValue() {
        return rightStick.getX();
    }
}
