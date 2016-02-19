package org.usfirst.frc2175.config;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CatapultShooterConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME =
            "catapultShooter.properties";
    private final Logger log = Logger.getLogger(getClass().getName());

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
        log.log(Level.CONFIG,
                "Set short shot delay to " + shortShotDelay + " seconds");
    }

    public double getShortShotDelay() {
        return shortShotDelay;
    }

    public void setShortShotDelay(double shortShotDelay) {
        this.shortShotDelay = shortShotDelay;
    }

}
