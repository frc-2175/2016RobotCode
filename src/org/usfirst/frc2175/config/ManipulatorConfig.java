package org.usfirst.frc2175.config;

import java.util.Properties;

public class ManipulatorConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME = "manipulator.properties";
    private double bootSpeed;

    @Override
    protected String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        double bootSpeed = getDoublePropertyValue("boot.speed", properties);
        this.bootSpeed = bootSpeed;
    }

    public double getBootSpeed() {
        return bootSpeed;
    }
}
