package org.usfirst.frc2175.subsystem.manipulator;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

import edu.wpi.first.wpilibj.CANTalon;

public class ManipulatorSubsystem extends BaseSubsystem {

    private CANTalon bootTalon;
    private double bootSpeed;

    public ManipulatorSubsystem(RobotConfig robotConfig) {
        bootTalon = robotConfig.getWiringConfig().getBootTalon();
        // FIXME what to do with this? getBootSpeed() commented out
        // bootSpeed = robotConfig.getWiringConfig().getBootSpeed();
    }

    public void setBootSpeed(double speed) {
        bootTalon.set(speed);
    }

    public void moveBootDown() {
        bootTalon.set(-bootSpeed);
    }

    public void moveBootUp() {
        bootTalon.set(bootSpeed);
    }

    public boolean isBootUp() {
        // TODO implement this method
        return false;
    }

    public boolean isBootDown() {
        // TODO implement this method
        return false;
    }

}
