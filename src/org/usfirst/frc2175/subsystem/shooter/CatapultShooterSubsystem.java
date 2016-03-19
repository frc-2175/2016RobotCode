package org.usfirst.frc2175.subsystem.shooter;

import org.usfirst.frc2175.config.CatapultShooterConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.WiringConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

public class CatapultShooterSubsystem extends BaseSubsystem {
    private final Solenoid rightCatapultSolenoid;
    private final Solenoid leftCatapultSolenoid;
    private final DigitalInput catapultDownSwitch;
    private final DigitalInput rollerbarIntakeOutSwitch;

    private double shortShotDelay;

    public CatapultShooterSubsystem(RobotConfig robotConfig) {
        WiringConfig wiringConfig = robotConfig.getWiringConfig();
        CatapultShooterConfig catapultShooterConfig =
                robotConfig.getCatapultShooterConfig();

        this.leftCatapultSolenoid = wiringConfig.getLeftCatapultSolenoid();
        this.rightCatapultSolenoid = wiringConfig.getRightCatapultSolenoid();

        this.catapultDownSwitch = wiringConfig.getCatapultDownSwitch();

        this.shortShotDelay = catapultShooterConfig.getShortShotDelay();
        this.rollerbarIntakeOutSwitch =
                wiringConfig.getRollerbarIntakeOutSwitch();
    }

    public void setCatapultPosition(boolean isUp) {
        if (isSafeToFireCatapult()) {
            leftCatapultSolenoid.set(isUp);
            rightCatapultSolenoid.set(isUp);
        }
    }

    protected boolean isSafeToFireCatapult() {
        boolean isSafe = isIntakeOut();
        return isSafe;
    }

    private boolean isIntakeOut() {
        return rollerbarIntakeOutSwitch.get();
    }

    public boolean isCatapultDown() {
        return catapultDownSwitch.get();
    }

    public double getShortShotDelay() {
        return shortShotDelay;
    }

    public void setShortShotDelay(double shortShotDelay) {
        this.shortShotDelay = shortShotDelay;
    }

}
