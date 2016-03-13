package org.usfirst.frc2175.subsystem.intake;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.WiringConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class RollerbarIntakeSubsystem extends BaseSubsystem {
    private CANTalon rollerbarIntakeRollerTalon;
    private CANTalon rollerbarIntakeLiftTalon;
    private DoubleSolenoid rollerbarIntakeSolenoid;
    private DigitalInput rollerbarIntakeInSwitch;
    private DigitalInput rollerbarIntakeOutSwitch;
    private DigitalInput catapultDownSwitch;

    public RollerbarIntakeSubsystem(RobotConfig robotConfig) {
        WiringConfig wiringConfig = robotConfig.getWiringConfig();

        this.rollerbarIntakeRollerTalon =
                wiringConfig.getRollerbarIntakeTalon();
        this.rollerbarIntakeLiftTalon =
                wiringConfig.getRollerbarIntakeLiftTalon();
        this.rollerbarIntakeSolenoid =
                wiringConfig.getRollerbarIntakeSolenoid();
        this.rollerbarIntakeInSwitch =
                wiringConfig.getRollerbarIntakeInSwitch();
        this.rollerbarIntakeOutSwitch =
                wiringConfig.getRollerbarIntakeOutSwitch();
        this.catapultDownSwitch = wiringConfig.getCatapultDownSwitch();
    }

    public void setRollerbarSpeed(double speed) {
        rollerbarIntakeRollerTalon.set(speed);
    }

    /**
     * Attempts to set the lift speed to the desired speed. This first passes
     * through the safety handler, so if the desired speed would lead to an
     * unsafe condition, it is set to stop instead.
     *
     * @param speed
     *            Desired speed to lift rollerbar at. Positives represent moving
     *            it inwards.
     */
    public void setRollerbarLiftSpeed(double speed) {
        double safeSpeed = determineSafeRollerbarLiftSpeed(speed);
        rollerbarIntakeLiftTalon.set(safeSpeed);
    }

    public boolean isIntakeCompletelyOut() {
        return rollerbarIntakeOutSwitch.get();
    }

    public boolean isIntakeCompletelyIn() {
        return rollerbarIntakeInSwitch.get();
    }

    /**
     * This method is used to ensure the intake is running at a safe speed, i.e.
     * not smashing into things or obstructing the catapult. This is determined
     * based off of three methods that represent the current state of the
     * intake. If any one of the methods returns true, the intake is safe to
     * run, and the output is the same as the desired value. If none of the
     * three conditions are met, the speed is set to 0, or not moving.
     *
     * @param speed
     *            The desired speed to run at
     * @return The adjusted speed that it is safe to run the intake at
     */
    protected double determineSafeRollerbarLiftSpeed(double speed) {
        double setSpeed;
        // if (isIntakeInMiddle() || isIntakeNotOutAndCommandedOut(speed)
        // || willIntakeClearCatapultAndNotSmashInwards(speed)) {
        // setSpeed = speed;
        // } else {
        // setSpeed = 0;
        // }
        // TODO Super Super temporary fix until the build people get us good
        // sensors
        setSpeed = speed;
        return setSpeed;
    }

    private boolean isCatapultDown() {
        return catapultDownSwitch.get();
        // TODO fix sensors and reenable
    }

    /**
     * Determines whether the intake is inbetween extremes
     *
     * @return True if not at either extreme
     */
    private boolean isIntakeInMiddle() {
        return !isIntakeCompletelyIn() && !isIntakeCompletelyOut();
    }

    /**
     * Determines whether the intake is both not completely out and commanded to
     * move outwards. Note that this returns false if the intake is all the way
     * out.
     *
     * @param speed
     *            Speed intake is commanded to run at
     */
    private boolean isIntakeNotOutAndCommandedOut(double speed) {
        return !isIntakeCompletelyOut() && speed > 0;
    }

    /**
     * Determines if the intake can both clear the catapult and not "smash"
     * inwards, meaning running inwards while already at the innermost allowed
     * position.
     *
     * @param speed
     *            Speed the intake is commanded to run at
     */
    private boolean willIntakeClearCatapultAndNotSmashInwards(double speed) {
        return isCatapultDown() && !isIntakeCompletelyIn() && speed < 0;
    }
}
