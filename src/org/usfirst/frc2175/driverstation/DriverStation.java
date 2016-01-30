package org.usfirst.frc2175.driverstation;

import org.usfirst.frc2175.config.JoysticksConfig;
import org.usfirst.frc2175.config.RobotConfig;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Represents the driver and weapons officer tools, aka "operator interface".
 */
public class DriverStation {
    private Joystick leftStick;
    private Joystick rightStick;
    private Joystick gamepad;
    private DeadbandCalculator deadbandCalculator;
    private double deadbandSize;

    public DriverStation(RobotConfig robotConfig, DeadbandCalculator deadbandCalculator) {
        JoysticksConfig joysticksConfig = robotConfig.getJoysticksConfig();

        leftStick = robotConfig.getJoysticksConfig().getLeftStick();
        rightStick = robotConfig.getJoysticksConfig().getRightStick();
        gamepad = robotConfig.getGamepadConfig().getGamepad();

        this.deadbandCalculator = deadbandCalculator;
        deadbandSize = joysticksConfig.getDeadbandSize();
    }

    public double getMoveValue() {
        double input = leftStick.getY();
        double deadbandedOutput = deadbandCalculator.calcDeadbandedOutput(input, deadbandSize);
        return deadbandedOutput;
    }

    public double getTurnValue() {
        double input = rightStick.getX();
        double deadbandedOutput = deadbandCalculator.calcDeadbandedOutput(input, deadbandSize);
        return deadbandedOutput;
    }

    public double getLeftValue() {
        double input = leftStick.getY();
        double deadbandedOutput = deadbandCalculator.calcDeadbandedOutput(input, deadbandSize);
        return deadbandedOutput;
    }

    public double getRightValue() {
        double input = rightStick.getY();
        double deadbandedOutput = deadbandCalculator.calcDeadbandedOutput(input, deadbandSize);
        return deadbandedOutput;
    }
}
