package org.usfirst.frc2175.util;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * This is a class that takes three CAN Talons and wraps them as one
 * SpeedController. Currently, it just sets them up as a standard
 * SpeedController, but it should be changed to set them up as a CAN
 * SpeedController.
 *
 * TODO Change this to implement CANSpeedController
 *
 * @author Max Haland
 *
 */

public class TalonGroup implements SpeedController {

    private CANTalon talon1;
    private CANTalon talon2;
    private CANTalon talon3;

    private boolean isInverted = false;

    public TalonGroup(CANTalon talon1, CANTalon talon2, CANTalon talon3) {
        this.talon1 = talon1;
        this.talon2 = talon2;
        this.talon3 = talon3;
    }

    @Override
    public void pidWrite(double output) {
        set(output);
    }

    @Override
    public double get() {
        if (talon1.get() != talon2.get() || talon1.get() != talon3.get()) {
            // TODO Switch this to proper logging
            System.out
            .println("Talon gets not matching! Something has gone seriously wrong!");
        }

        return talon1.get();
    }

    @Override
    public void set(double speed, byte syncGroup) {
        talon1.set(speed, syncGroup);
        talon2.set(speed, syncGroup);
        talon3.set(speed, syncGroup);
    }

    @Override
    public void set(double speed) {
        talon1.set(speed);
        talon2.set(speed);
        talon3.set(speed);
    }

    @Override
    public void setInverted(boolean isInverted) {
        this.isInverted = isInverted;
        talon1.setInverted(isInverted);
        talon2.setInverted(isInverted);
        talon3.setInverted(isInverted);
    }

    @Override
    public boolean getInverted() {
        return isInverted;
    }

    @Override
    public void disable() {
        talon1.disable();
        talon2.disable();
        talon3.disable();
    }

    @Override
    public void stopMotor() {
        set(0);
    }

}
