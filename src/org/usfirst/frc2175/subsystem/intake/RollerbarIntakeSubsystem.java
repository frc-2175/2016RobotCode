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
    }

    public void setRollerbarSpeed(double speed) {
        rollerbarIntakeRollerTalon.set(speed);
    }

    public void setRollerbarLiftSpeed(double speed) {
        rollerbarIntakeLiftTalon.set(speed);
    }

    public void setIntakePosition(boolean isOut) {
        if (isOut) {
            rollerbarIntakeSolenoid.set(DoubleSolenoid.Value.kForward);
        } else {
            rollerbarIntakeSolenoid.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public boolean isCompletelyOut() {
        return rollerbarIntakeOutSwitch.get();
    }

    public boolean isCompletelyIn() {
        return rollerbarIntakeInSwitch.get();
    }
}
