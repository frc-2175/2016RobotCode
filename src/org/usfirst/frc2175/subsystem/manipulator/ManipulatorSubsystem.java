package org.usfirst.frc2175.subsystem.manipulator;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

public class ManipulatorSubsystem extends BaseSubsystem {

    private DigitalInput isBootUpSwitch;
    private DigitalInput isBootDownSwitch;

    private CANTalon bootTalon;

    public ManipulatorSubsystem(RobotConfig robotConfig) {
        bootTalon = robotConfig.getWiringConfig().getBootTalon();
        isBootUpSwitch = robotConfig.getWiringConfig().getIsBootUpSwitch();
        isBootDownSwitch = robotConfig.getWiringConfig().getIsBootDownSwitch();
    }

    public void setBootSpeed(double speed) {
        bootTalon.set(speed);
    }

    public boolean isBootUp() {
        return isBootUpSwitch.get();
    }

    public boolean isBootDown() {
        return isBootDownSwitch.get();
    }

}
