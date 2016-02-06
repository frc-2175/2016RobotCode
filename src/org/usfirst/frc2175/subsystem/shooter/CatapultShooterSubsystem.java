package org.usfirst.frc2175.subsystem.shooter;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class CatapultShooterSubsystem extends BaseSubsystem {

    private DoubleSolenoid rightCatapultSolenoid;
    private DoubleSolenoid leftCatapultSolenoid;
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
            leftCatapultSolenoid.set(DoubleSolenoid.Value.kForward);
            rightCatapultSolenoid.set(DoubleSolenoid.Value.kForward);
        } else {
            leftCatapultSolenoid.set(DoubleSolenoid.Value.kReverse);
            rightCatapultSolenoid.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public boolean isCatapultDown() {
        return catapultDownSwitch.get();
    }

    public boolean isCatapultUp() {
        return catapultUpSwitch.get();
    }

}
