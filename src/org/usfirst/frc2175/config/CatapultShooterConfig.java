package org.usfirst.frc2175.config;

import java.util.Properties;

public class CatapultShooterConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME =
            "catapultShooter.properties";

    private double rampShotDelay;
    private double batterShotDelay;
    private double middleShotDelay;

    @Override
    public String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        double rampShotDelay =
                getDoublePropertyValue("shooter.rampShot.delay", properties);
        this.rampShotDelay = rampShotDelay;

        double batterShotDelay =
                getDoublePropertyValue("shooter.batterShot.delay", properties);
        this.batterShotDelay = batterShotDelay;

        double middleShotDelay =
                getDoublePropertyValue("shooter.middleShot.delay", properties);
        this.middleShotDelay = middleShotDelay;
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
