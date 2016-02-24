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
    private final DigitalInput catapultUpSwitch;
    private final DigitalInput catapultDownSwitch;

    private double shortShotDelay;

    public CatapultShooterSubsystem(RobotConfig robotConfig) {
        WiringConfig wiringConfig = robotConfig.getWiringConfig();
        CatapultShooterConfig catapultShooterConfig =
                robotConfig.getCatapultShooterConfig();

        this.leftCatapultSolenoid = wiringConfig.getLeftCatapultSolenoid();
        this.rightCatapultSolenoid = wiringConfig.getRightCatapultSolenoid();

        this.catapultUpSwitch = wiringConfig.getCatapultUpSwitch();
        this.catapultDownSwitch = wiringConfig.getCatapultDownSwitch();

        this.shortShotDelay = catapultShooterConfig.getShortShotDelay();
    }

    public void setCatapultPosition(boolean isUp) {
        leftCatapultSolenoid.set(isUp);
        rightCatapultSolenoid.set(isUp);
    }

    public boolean isCatapultDown() {
        return catapultDownSwitch.get();
    }

    public boolean isCatapultUp() {
        return catapultUpSwitch.get();
    }

    public double getShortShotDelay() {
        return shortShotDelay;
    }

    public void setShortShotDelay(double shortShotDelay) {
        this.shortShotDelay = shortShotDelay;
    }
}
