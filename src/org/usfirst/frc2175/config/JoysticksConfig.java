package org.usfirst.frc2175.config;

import java.util.Properties;

/**
 * Joystick configuration values - "driving" controls.
 *
 * Set the values in the joysticks properties file.
 *
 * Do not use values directly in code, use these configuration methods.
 */
public class JoysticksConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME = "joysticks.properties";

    @Override
    public String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        // TODO implement me
        // - add private instance variables for the values
        // - add get() methods for them
    }

    public int getLeftStickPort() {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getRightStickPort() {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getGamepadPortPort() {
        // TODO Auto-generated method stub
        return 0;
    }
}
