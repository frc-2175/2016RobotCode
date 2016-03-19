package org.usfirst.frc2175.config;

import java.util.Properties;

public class IntakeConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME = "intake.properties";
    private double rollerbarSpeedIn;
    private double rollerbarSpeedOut;
    private double liftIntakeSpeed;

    @Override
    public String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        double rollerbarSpeedIn =
                getDoublePropertyValue("rollerbar.intakeSpeed.in", properties);
        this.rollerbarSpeedIn = rollerbarSpeedIn;
        double rollerbarSpeedOut =
                getDoublePropertyValue("rollerbar.intakeSpeed.out", properties);
        this.rollerbarSpeedOut = rollerbarSpeedOut;
        double liftIntakeSpeed =
                getDoublePropertyValue("liftIntake.speed", properties);
        this.liftIntakeSpeed = liftIntakeSpeed;
    }

    public double getLiftIntakeSpeed() {
        return liftIntakeSpeed;
    }

    public double getRollerbarSpeedIn() {
        return rollerbarSpeedIn;
    }

    public double getRollerbarSpeedOut() {
        return rollerbarSpeedOut;
    }

}
