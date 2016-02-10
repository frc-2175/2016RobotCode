package org.usfirst.frc2175.subsystem.intake;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.WiringConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;

public class RollerbarIntakeSubsystem extends BaseSubsystem {
    private Talon rollerbarIntakeRollerTalon;
    private DoubleSolenoid rollerbarIntakeSolenoid;
    private DigitalInput rollerbarIntakeInSwitch;
    private DigitalInput rollerbarIntakeOutSwitch;

    public RollerbarIntakeSubsystem(RobotConfig robotConfig) {
        WiringConfig wiringConfig = robotConfig.getWiringConfig();

        this.rollerbarIntakeRollerTalon = wiringConfig.getRollerbarIntakeTalon();
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
