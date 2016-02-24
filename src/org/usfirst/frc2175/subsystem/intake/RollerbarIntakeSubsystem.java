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

    protected double determineSafeRollerbarLiftSpeed(double speed) {
        double setSpeed;
        if (isIntakeInMiddle() || isIntakeNotOutAndCommandedOut(speed)
                || willIntakeClearCatapultAndNotSmashInwards(speed)) {
            setSpeed = speed;
        } else {
            setSpeed = 0;
        }

        return setSpeed;
    }

    private boolean isCatapultDown() {
        return catapultDownSwitch.get();
    }

    private boolean isIntakeInMiddle() {
        return !isIntakeCompletelyIn() && !isIntakeCompletelyOut();
    }

    private boolean isIntakeNotOutAndCommandedOut(double speed) {
        return !isIntakeCompletelyOut() && speed > 0;
    }

    private boolean willIntakeClearCatapultAndNotSmashInwards(double speed) {
        return isCatapultDown() && !isIntakeCompletelyIn() && speed < 0;
    }
}
