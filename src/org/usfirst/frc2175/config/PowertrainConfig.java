package org.usfirst.frc2175.config;

import java.util.Properties;

public class PowertrainConfig extends BaseConfig {

    private static final String PROPERTY_FILE_NAME = "powertrain.properties";

    private int[] stateLow;
    private int[] stateHigh;
    private int[] stateClimb_Neutral;
    private int[] stateClimb_Low;

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
        this.stateClimb_Neutral = getIntArrayPropertyValue(
                "shifters.state.climb_neutral", properties);
        this.stateClimb_Low = getIntArrayPropertyValue(
                "shifters.state.climb_low", properties);

        if (stateLow.length != stateHigh.length
                || stateLow.length != stateClimb_Neutral.length
                || stateLow.length != stateClimb_Low.length) {
            throw new IllegalStateException(
                    "Shifter state arrays are different lengths!");
        }

    }

    public int[] getStateClimb_Low() {
        return stateClimb_Low;
    }

    public int[] getStateLow() {
        return stateLow;
    }

    public int[] getStateHigh() {
        return stateHigh;
    }

    public int[] getStateClimb_Neutral() {
        return stateClimb_Neutral;
    }

}
