package org.usfirst.frc2175.subsystem.manipulator;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

import edu.wpi.first.wpilibj.CANTalon;

public class ManipulatorSubsystem extends BaseSubsystem {

    private CANTalon bootTalon;

    public ManipulatorSubsystem(RobotConfig robotConfig) {
        bootTalon = robotConfig.getWiringConfig().getBootTalon();
    }

    public void setBootSpeed(double speed) {
        bootTalon.set(speed);
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
