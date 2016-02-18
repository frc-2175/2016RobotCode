package org.usfirst.frc2175.config;

import java.util.Properties;

public class PowertrainConfig extends BaseConfig {

    private static final String PROPERTY_FILE_NAME = "powertrain.properties";

    private int[] stateLow;
    private int[] stateHigh;
    private int[] stateClimb;
    private int[] stateNeutral;

    @Override
    protected String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties)
            throws IllegalArgumentException {
        this.stateLow =
                getIntArrayPropertyValue("shifters.state.low", properties);
        this.stateHigh =
                getIntArrayPropertyValue("shifters.state.high", properties);
        this.stateClimb =
                getIntArrayPropertyValue("shifters.state.climb", properties);
        this.stateNeutral =
                getIntArrayPropertyValue("shifters.state.neutral", properties);
        if (stateLow.length != stateHigh.length
                || stateLow.length != stateNeutral.length
                || stateLow.length != stateClimb.length) {
            throw new IllegalStateException(
                    "Shifter state arrays are different lengths!");
        }

    }

    public int[] getStateLow() {
        return stateLow;
    }

    public int[] getStateHigh() {
        return stateHigh;
    }

    public int[] getStateClimb() {
        return stateClimb;
    }

    public int[] getStateNeutral() {
        return stateNeutral;
    }

}
