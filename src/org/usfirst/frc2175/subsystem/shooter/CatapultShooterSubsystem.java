package org.usfirst.frc2175.subsystem.shooter;

import java.util.logging.Logger;

import org.usfirst.frc2175.config.CatapultShooterConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.WiringConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CatapultShooterSubsystem extends BaseSubsystem {
    private final Logger log = Logger.getLogger(getClass().getName());

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

    public void setCatapultPosition(boolean isUp) {
        // if (isSafeToFireCatapult()) {
        leftCatapultSolenoid.set(isUp);
        rightCatapultSolenoid.set(isUp);
        // }
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
        default:
            log.severe("getWantedShotDelay: ShotType='" + currentShot
                    + "' not coded for!");
        }
        return shotDelay;
    }

    public void setShotType(ShotType shotType) {
        this.currentShot = shotType;
        updateSmartDashboardShotDisplay();
    }

    public void cycleShotType() {
        switch (this.currentShot) {
        case BATTER:
            setShotType(ShotType.MIDDLE);
            break;
        case MIDDLE:
            setShotType(ShotType.RAMP);
            break;
        case RAMP:
            setShotType(ShotType.BATTER);
            break;
        default:
            setShotType(ShotType.BATTER);
            log.severe("cycleShotType: ShotType='" + currentShot
                    + "' not coded for!");
        }
        updateSmartDashboardShotDisplay();
    }

    private void updateSmartDashboardShotDisplay() {
        SmartDashboard.putBoolean("MIDDLE", false);
        SmartDashboard.putBoolean("RAMP", false);
        SmartDashboard.putBoolean("BATTER", false);

        switch (this.currentShot) {
        case BATTER:
            SmartDashboard.putBoolean("BATTER", true);
            break;
        case MIDDLE:
            SmartDashboard.putBoolean("MIDDLE", true);
            break;
        case RAMP:
            SmartDashboard.putBoolean("RAMP", true);
            break;
        default:
            log.severe("updateSmartDashboardShotDisplay: ShotType='"
                    + currentShot + "' not coded for!");
        }
    }

    public ShotType getCurrentShot() {
        return currentShot;
    }

    public double getMiddleShotDelay() {
        return middleShotDelay;
    }

    public double getRampShotDelay() {
        return rampShotDelay;
    }

    public double getBatterShotDelay() {
        return batterShotDelay;
    }
}
