package org.usfirst.frc2175.driverstation;

import java.util.logging.Logger;

import org.usfirst.frc2175.config.JoysticksConfig;
import org.usfirst.frc2175.config.RobotConfig;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Represents the driver and weapons officer tools, aka "operator interface".
 */
public class DriverStation {
    private final Logger log = Logger.getLogger(getClass().getName());
    //
    // private Joystick leftStick;
    // private Joystick rightStick;
    private Joystick driverGamepad;

    private DeadbandCalculator deadbandCalculator;
    private double deadbandSize;

    public DriverStation(RobotConfig robotConfig,
            DeadbandCalculator deadbandCalculator) {
        log.info("Configuring class=" + getClass());

        JoysticksConfig joysticksConfig = robotConfig.getJoysticksConfig();

        // leftStick = robotConfig.getJoysticksConfig().getLeftStick();
        // rightStick = robotConfig.getJoysticksConfig().getRightStick();
        driverGamepad = robotConfig.getGamepadConfig().getDriverGamepad();

        this.deadbandCalculator = deadbandCalculator;
        deadbandSize = joysticksConfig.getDeadbandSize();

    }

    public double getMoveValue() {
        double input = -driverGamepad.getRawAxis(2);
        double deadbandedOutput =
                deadbandCalculator.calcDeadbandedOutput(input, deadbandSize);
        return deadbandedOutput;
    }

    public double getTurnValue() {
        double input = driverGamepad.getRawAxis(4);
        double deadbandedOutput =
                deadbandCalculator.calcDeadbandedOutput(input, deadbandSize);
        return deadbandedOutput;
    }

    // public double getMoveValue() {
    // double input = -driverGamepad.getY(Hand.kLeft);
    // double deadbandedOutput =
    // deadbandCalculator.calcDeadbandedOutput(input, deadbandSize);
    // return deadbandedOutput;
    // }
    //
    // public double getTurnValue() {
    // double input = driverGamepad.getX(Hand.kRight);
    // double deadbandedOutput =
    // deadbandCalculator.calcDeadbandedOutput(input, deadbandSize);
    // return deadbandedOutput;
    // }

    // public double getMoveValue() {
    // double input = -leftStick.getY();
    // double deadbandedOutput =
    // deadbandCalculator.calcDeadbandedOutput(input, deadbandSize);
    // return deadbandedOutput;
    // }
    //
    // public double getTurnValue() {
    // double input = rightStick.getX();
    // double deadbandedOutput =
    // deadbandCalculator.calcDeadbandedOutput(input, deadbandSize);
    // return deadbandedOutput;
    // }
    //
    public double getLeftValue() {
        double input = -driverGamepad.getRawAxis(1);
        double deadbandedOutput =
                deadbandCalculator.calcDeadbandedOutput(input, deadbandSize);
        return deadbandedOutput;
    }

    public double getRightValue() {
        double input = -driverGamepad.getRawAxis(4);
        double deadbandedOutput =
                deadbandCalculator.calcDeadbandedOutput(input, deadbandSize);
        return deadbandedOutput;
    }
}
