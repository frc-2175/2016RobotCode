package org.usfirst.frc2175.config;

import java.util.Properties;

public class IntakeConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME = "intake.properties";
    private double sideBeltSpeedForward;
    private double sideBeltSpeedReverse;
    private double mainBeltSpeedForward;
    private double mainBeltSpeedReverse;
    private double rollerbarSpeedIn;
    private double rollerbarSpeedOut;

    @Override
    public String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        double sideBeltSpeedForward = getDoublePropertyValue(
                "dream.intake.sideBelt.forward", properties);
        this.sideBeltSpeedForward = sideBeltSpeedForward;
        double sideBeltSpeedReverse = getDoublePropertyValue(
                "dream.intake.sideBelt.reverse", properties);
        this.sideBeltSpeedReverse = sideBeltSpeedReverse;
        double mainBeltSpeedForward = getDoublePropertyValue(
                "dream.intake.sideBelt.forward", properties);
        this.mainBeltSpeedForward = mainBeltSpeedForward;
        double mainBeltSpeedReverse = getDoublePropertyValue(
                "dream.intake.sideBelt.reverse", properties);
        this.mainBeltSpeedReverse = mainBeltSpeedReverse;
        double rollerbarSpeedIn =
                getDoublePropertyValue("rollerbar.intakeSpeed.in", properties);
        this.rollerbarSpeedIn = rollerbarSpeedIn;
        double rollerbarSpeedOut =
                getDoublePropertyValue("rollerbar.intakeSpeed.out", properties);
        this.rollerbarSpeedOut = rollerbarSpeedOut;
    }

    public double getRollerbarSpeedIn() {
        return rollerbarSpeedIn;
    }

    public double getRollerbarSpeedOut() {
        return rollerbarSpeedOut;
    }

    public double getSideBeltSpeedForward() {
        return sideBeltSpeedForward;
    }

    public double getSideBeltSpeedReverse() {
        return sideBeltSpeedReverse;
    }

    public double getMainBeltSpeedForward() {
        return mainBeltSpeedForward;
    }

    public double getMainBeltSpeedReverse() {
        return mainBeltSpeedReverse;
    }

}
