package org.usfirst.frc2175.subsystem.manipulator;

import org.usfirst.frc2175.config.ManipulatorConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.WiringConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

public class ManipulatorSubsystem extends BaseSubsystem {
    private final DigitalInput isBootUpSwitch;
    private final DigitalInput isBootDownSwitch;

    private final CANTalon bootTalon;

    private final double bootSpeed;

    public ManipulatorSubsystem(RobotConfig robotConfig) {
        WiringConfig wiringConfig = robotConfig.getWiringConfig();
        ManipulatorConfig manipulatorConfig =
                robotConfig.getManipulatorConfig();

        bootTalon = wiringConfig.getBootTalon();
        isBootUpSwitch = wiringConfig.getIsBootUpSwitch();
        isBootDownSwitch = wiringConfig.getIsBootDownSwitch();

        bootSpeed = manipulatorConfig.getBootSpeed();
    }

    protected double determineSafetyCheckedBootSpeed(double speed) {
        double setSpeed;
        if (willBootNotSmashDown() || willBootNotSmashUp()
                || isBootInMiddle()) {
            setSpeed = speed;
        } else {
            setSpeed = 0;
        }
        return setSpeed;
    }

    private boolean willBootNotSmashUp() {
        return (isBootUp() && bootSpeed < 0);
    }

    private boolean willBootNotSmashDown() {
        return (isBootDown() && bootSpeed > 0);
    }

    private boolean isBootInMiddle() {
        return (!isBootDown() && !isBootUp());
    }

    public void setBootSpeed(double speed) {
        bootTalon.set(determineSafetyCheckedBootSpeed(speed));
    }

    public void moveBootDown() {
        bootTalon.set(-bootSpeed);
    }

    public void moveBootUp() {
        bootTalon.set(bootSpeed);
    }

    public boolean isBootUp() {
        return isBootUpSwitch.get();
    }

    public boolean isBootDown() {
        return isBootDownSwitch.get();
    }
}
