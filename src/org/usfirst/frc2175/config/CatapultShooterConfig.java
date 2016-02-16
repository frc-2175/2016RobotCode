package org.usfirst.frc2175.config;

import java.util.Properties;

public class CatapultShooterConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME =
            "catapultShooter.properties";
    private double shortShotDelay;

    @Override
    public String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        double shortShotDelay =
                getDoublePropertyValue("shooter.shortShot.delay", properties);
        this.shortShotDelay = shortShotDelay;
    }

    public double getShortShotDelay() {
        return shortShotDelay;
    }

    public void setShortShotDelay(double shortShotDelay) {
        this.shortShotDelay = shortShotDelay;
    }

}
