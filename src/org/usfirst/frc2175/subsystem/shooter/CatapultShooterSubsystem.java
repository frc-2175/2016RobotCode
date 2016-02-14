package org.usfirst.frc2175.subsystem.shooter;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

public class CatapultShooterSubsystem extends BaseSubsystem {

    private Solenoid rightCatapultSolenoid;
    private Solenoid leftCatapultSolenoid;
    private Solenoid middleTopCatapultSolenoid;
    private Solenoid middleBottomCatapultSolenoid;
    private DigitalInput catapultUpSwitch;
    private DigitalInput catapultDownSwitch;

    public CatapultShooterSubsystem(RobotConfig robotConfig) {
        leftCatapultSolenoid =
                robotConfig.getWiringConfig().getLeftCatapultSolenoid();
        rightCatapultSolenoid =
                robotConfig.getWiringConfig().getRightCatapultSolenoid();
        middleTopCatapultSolenoid =
                robotConfig.getWiringConfig().getMiddleTopCatapultSolenoid();
        middleBottomCatapultSolenoid =
                robotConfig.getWiringConfig().getMiddleBottomCatapultSolenoid();

        catapultUpSwitch = robotConfig.getWiringConfig().getCatapultUpSwitch();
        catapultDownSwitch =
                robotConfig.getWiringConfig().getCatapultDownSwitch();

    }

    public void setLeftRightSolenoids(boolean isExtended) {
        leftCatapultSolenoid.set(isExtended);
        rightCatapultSolenoid.set(isExtended);
    }

    public void setMiddleTopSolenoid(boolean isOn) {
        middleTopCatapultSolenoid.set(isOn);
    }

    public void setMiddleBottomSolenoid(boolean isOn) {
        middleBottomCatapultSolenoid.set(isOn);
    }

    public boolean isCatapultDown() {
        return catapultDownSwitch.get();
    }

    public boolean isCatapultUp() {
        return catapultUpSwitch.get();
    }

}
