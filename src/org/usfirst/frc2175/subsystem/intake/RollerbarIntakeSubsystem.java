package org.usfirst.frc2175.subsystem.intake;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.WiringConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;

public class RollerbarIntakeSubsystem extends BaseSubsystem {
    private Talon rollerbarIntakeTalon;
    private DoubleSolenoid rollerbarIntakeSolenoid;
    private DigitalInput rollerbarIntakeUpSwitch;
    private DigitalInput rollerbarIntakeDownSwitch;

    public RollerbarIntakeSubsystem(RobotConfig robotConfig) {
        WiringConfig wiringConfig = robotConfig.getWiringConfig();

        this.rollerbarIntakeTalon = wiringConfig.getRollerbarIntakeTalon();
        this.rollerbarIntakeSolenoid =
                wiringConfig.getRollerbarIntakeSolenoid();
        this.rollerbarIntakeUpSwitch =
                wiringConfig.getRollerbarIntakeUpSwitch();
        this.rollerbarIntakeDownSwitch =
                wiringConfig.getRollerbarIntakeDownSwitch();
    }

    public void setRollerbarSpeed(double speed) {
        rollerbarIntakeTalon.set(speed);
    }

    public void setIntakePosition(boolean isOut) {
        if (isOut) {
            rollerbarIntakeSolenoid.set(DoubleSolenoid.Value.kReverse);
        } else {
            rollerbarIntakeSolenoid.set(DoubleSolenoid.Value.kForward);
        }
    }

    public boolean isCompletelyOut() {
        return rollerbarIntakeDownSwitch.get();
    }

    public boolean isCompletelyIn() {
        return rollerbarIntakeUpSwitch.get();
    }
}
