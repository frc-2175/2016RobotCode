package org.usfirst.frc2175.config;

/**
 * Robot configuration values.
 */
public class RobotConfig {
    private final GamepadConfig gamepadConfig = new GamepadConfig();
    private final JoysticksConfig joystickConfig = new JoysticksConfig();
    private final WiringConfig wiringConfig = new WiringConfig();
    private final VisionProcessingConfig visionProcessingConfig = new VisionProcessingConfig();

    public GamepadConfig getGamepadConfig() {
        return gamepadConfig;
    }

    public JoysticksConfig getJoysticksConfig() {
        return joystickConfig;
    }

    public WiringConfig getWiringConfig() {
        return wiringConfig;
    }

    public VisionProcessingConfig getVisionProcessingConfig() {
        return visionProcessingConfig;
    }
}
