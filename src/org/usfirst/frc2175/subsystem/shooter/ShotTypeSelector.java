package org.usfirst.frc2175.subsystem.shooter;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.usfirst.frc2175.config.CatapultShooterConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShotTypeSelector {
    private final Logger log = Logger.getLogger(getClass().getName());

    private final double batterShotDelay;
    private final double middleShotDelay;
    private final double rampShotDelay;

    private ShotType currentShot = ShotType.BATTER;

    public ShotTypeSelector(CatapultShooterConfig catapultShooterConfig) {
        this.rampShotDelay = catapultShooterConfig.getRampShotDelay();
        this.batterShotDelay = catapultShooterConfig.getBatterShotDelay();
        this.middleShotDelay = catapultShooterConfig.getMiddleShotDelay();
    }

    public void setShotType(ShotType shotType) {
        this.currentShot = shotType;
        updateSmartDashboardShotDisplay();
    }

    public double getShotDelay(ShotType shotType) {
        final double shotDelay;

        switch (shotType) {
        case BATTER:
            shotDelay = batterShotDelay;
            break;
        case MIDDLE:
            shotDelay = middleShotDelay;
            break;
        case RAMP:
            shotDelay = rampShotDelay;
            break;
        default:
            shotDelay = 1;
            log.severe("getShotDelay: ShotType='" + shotType
                    + "' not coded for! Using default delay of 1.");
        }

        return shotDelay;
    }

    public double getCurrentShotDelay() {
        return getShotDelay(currentShot);
    }

    public ShotType cycleShotType() {
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
            ShotType oldShotType = currentShot;
            setShotType(ShotType.BATTER);
            log.severe("cycleShotType: ShotType='" + oldShotType
                    + "' not coded for! Using default of '" + currentShot
                    + "'");
        }

        log.log(Level.FINE, "Cycled catapult shot to '" + currentShot + "'");

        updateSmartDashboardShotDisplay();

        return currentShot;
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
}
