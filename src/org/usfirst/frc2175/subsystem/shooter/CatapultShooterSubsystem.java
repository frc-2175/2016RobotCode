package org.usfirst.frc2175.subsystem.shooter;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

public class CatapultShooterSubsystem extends BaseSubsystem {

    private Solenoid rightCatapultSolenoid;
    private Solenoid leftCatapultSolenoid;
    private DigitalInput catapultUpSwitch;
    private DigitalInput catapultDownSwitch;

    public CatapultShooterSubsystem(RobotConfig robotConfig) {
        leftCatapultSolenoid =
                robotConfig.getWiringConfig().getLeftCatapultSolenoid();
        rightCatapultSolenoid =
                robotConfig.getWiringConfig().getRightCatapultSolenoid();

        catapultUpSwitch = robotConfig.getWiringConfig().getCatapultUpSwitch();
        catapultDownSwitch =
                robotConfig.getWiringConfig().getCatapultDownSwitch();

    }

    public void setCatapultPosition(boolean isUp) {
        if (isUp) {
            leftCatapultSolenoid.set(true);
            rightCatapultSolenoid.set(true);
        } else {
            leftCatapultSolenoid.set(false);
            rightCatapultSolenoid.set(false);
        }
    }

    public boolean isCatapultDown() {
        return catapultDownSwitch.get();
    }

    public boolean isCatapultUp() {
        return catapultUpSwitch.get();
    }

}
