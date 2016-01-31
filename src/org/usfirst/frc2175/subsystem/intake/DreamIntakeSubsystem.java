package org.usfirst.frc2175.subsystem.intake;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

import edu.wpi.first.wpilibj.Talon;

public class DreamIntakeSubsystem extends BaseSubsystem {
    private Talon dreamIntakeSideBeltTalon;
    private Talon dreamIntakeMainBeltTalon;
    private Talon dreamIntakeLiftTalon;

    public DreamIntakeSubsystem(RobotConfig robotConfig) {
        dreamIntakeSideBeltTalon =
                robotConfig.getWiringConfig().getDreamIntakeSideBeltTalon();
        dreamIntakeMainBeltTalon =
                robotConfig.getWiringConfig().getDreamIntakeMainBeltTalon();
        dreamIntakeLiftTalon =
                robotConfig.getWiringConfig().getDreamIntakeLiftTalon();
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
        // TODO Fill in
        return false;
    }

    public boolean isCompletelyIn() {
        // TODO Fill in
        return false;
    }

}
