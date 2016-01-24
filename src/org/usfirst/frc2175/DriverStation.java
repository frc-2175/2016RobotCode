package org.usfirst.frc2175;

import org.usfirst.frc2175.config.JoysticksConfig;
import org.usfirst.frc2175.config.RobotConfig;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Represents the driver and weapons officer tools, aka "operator interface".
 */
public class DriverStation {
    public Joystick leftStick;
    public Joystick rightStick;
    public Joystick gamepad;

    public DriverStation(RobotConfig robotConfig) {
        JoysticksConfig joysticksConfig = robotConfig.getJoysticksConfig();
        int leftStickPort = joysticksConfig.getLeftStickPort();
        int rightStickPort = joysticksConfig.getRightStickPort();
        int gamepadPort = joysticksConfig.getGamepadPortPort();

        leftStick = new Joystick(leftStickPort);
        rightStick = new Joystick(rightStickPort);
        gamepad = new Joystick(gamepadPort);
    }

    public double getMoveValue() {
        // TODO Add deadband
        return leftStick.getY();
    }

    public double getTurnValue() {
        // TODO Add deadband
        return rightStick.getX();
    }

    public double getLeftValue() {
        // TODO Add deadband
        return leftStick.getY();
    }

    public double getRightValue() {
        // TODO Add deadband
        return rightStick.getY();
    }
}
