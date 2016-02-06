package org.usfirst.frc2175.subsystem.intake;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.WiringConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;

public class DreamIntakeSubsystem extends BaseSubsystem {
    private Talon dreamIntakeSideBeltTalon;
    private Talon dreamIntakeMainBeltTalon;
    private Talon dreamIntakeLiftTalon;
    private DigitalInput dreamIntakeUpSwitch;
    private DigitalInput dreamIntakeDownSwitch;

    public DreamIntakeSubsystem(RobotConfig robotConfig) {
        WiringConfig wiringConfig = robotConfig.getWiringConfig();

        dreamIntakeSideBeltTalon = wiringConfig.getDreamIntakeSideBeltTalon();
        dreamIntakeMainBeltTalon = wiringConfig.getDreamIntakeMainBeltTalon();
        dreamIntakeLiftTalon = wiringConfig.getDreamIntakeLiftTalon();
        this.dreamIntakeUpSwitch = wiringConfig.getDreamIntakeUpSwitch();
        this.dreamIntakeDownSwitch = wiringConfig.getDreamIntakeDownSwitch();
    }

    public void setMainBeltSpeed(double speed) {
        dreamIntakeMainBeltTalon.set(speed);
    }

    public void setSideBeltSpeed(double speed) {
        dreamIntakeSideBeltTalon.set(speed);
    }

    public void setLiftSpeed(double speed) {
        dreamIntakeLiftTalon.set(speed);
    }

    public boolean isCompletelyOut() {
        return dreamIntakeDownSwitch.get();
    }

    public boolean isCompletelyIn() {
        return dreamIntakeUpSwitch.get();
    }
}
