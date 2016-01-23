package org.usfirst.frc2175;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
    public Joystick leftStick;
    public Joystick rightStick;
    public Joystick gamepad;

    public OI() {
        leftStick = new Joystick(0);
        rightStick = new Joystick(1);
        gamepad = new Joystick(2);
    }

    public double getMoveValue() {
        // TODO Add deadband
        return leftStick.getY();
    }

    public double getTurnValue() {
        // TODO Add deadband
        return rightStick.getX();
    }
}
