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

    private double rampShotDelay;
    private double batterShotDelay;
    private double middleShotDelay;

    private ShotType currentShot = ShotType.BATTER;

    public CatapultShooterSubsystem(RobotConfig robotConfig) {
        WiringConfig wiringConfig = robotConfig.getWiringConfig();
        CatapultShooterConfig catapultShooterConfig =
                robotConfig.getCatapultShooterConfig();

        this.leftCatapultSolenoid = wiringConfig.getLeftCatapultSolenoid();
        this.rightCatapultSolenoid = wiringConfig.getRightCatapultSolenoid();

        this.catapultDownSwitch = wiringConfig.getCatapultDownSwitch();

        this.rampShotDelay = catapultShooterConfig.getRampShotDelay();
        this.batterShotDelay = catapultShooterConfig.getBatterShotDelay();
        this.middleShotDelay = catapultShooterConfig.getMiddleShotDelay();

        this.rollerbarIntakeOutSwitch =
                wiringConfig.getRollerbarIntakeOutSwitch();
    }

    public double getMiddleShotDelay() {
        return middleShotDelay;
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

    public double getRampShotDelay() {
        return rampShotDelay;
    }

    public double getBatterShotDelay() {
        return batterShotDelay;
    }

    public double getWantedShotDelay() {
        double shotDelay = 1;
        switch (this.currentShot) {
        case BATTER:
            shotDelay = getBatterShotDelay();
            break;
        case MIDDLE:
            shotDelay = getMiddleShotDelay();
            break;
        case RAMP:
            shotDelay = getRampShotDelay();
            break;
        }
        return shotDelay;
    }

    public void setShotType(ShotType shotType) {
        this.currentShot = shotType;
    }

    public void cycleShotType() {
        if (this.currentShot == ShotType.BATTER) {
            this.currentShot = ShotType.MIDDLE;
        }
        if (this.currentShot == ShotType.MIDDLE) {
            this.currentShot = ShotType.RAMP;
        }
        if (this.currentShot == ShotType.RAMP) {
            this.currentShot = ShotType.BATTER;
        }
    }

    private enum ShotType {
        BATTER, MIDDLE, RAMP;
    }

}
