package org.usfirst.frc2175.config;

/**
 * Robot configuration values.
 */
public class RobotConfig {
    // WARNING: must place LoggingConfiguration first!
    // so that logging configured ASAP to capture and other problems
    private final LoggingConfiguration loggingConfiguration =
            new LoggingConfiguration();

    private final GamepadConfig gamepadConfig = new GamepadConfig();
    private final JoysticksConfig joystickConfig = new JoysticksConfig();
    private final WiringConfig wiringConfig = new WiringConfig();
    private final VisionProcessingConfig visionProcessingConfig =
            new VisionProcessingConfig();
    private final ControlLoopConfig controlLoopConfig = new ControlLoopConfig();
    private final IntakeConfig intakeConfig = new IntakeConfig();
    private final CatapultShooterConfig catapultShooterConfig =
            new CatapultShooterConfig();
    private final ManipulatorConfig manipulatorConfig = new ManipulatorConfig();

    private final PowertrainConfig powertrainConfig = new PowertrainConfig();

    public PowertrainConfig getPowertrainConfig() {
        return powertrainConfig;
    }

    public LoggingConfiguration getLoggingConfiguration() {
        return loggingConfiguration;
    }

    public GamepadConfig getGamepadConfig() {
        return gamepadConfig;
    }

    public JoysticksConfig getJoysticksConfig() {
        return joystickConfig;
    }

    public CatapultShooterConfig getCatapultShooterConfig() {
        return catapultShooterConfig;
    }

    public WiringConfig getWiringConfig() {
        return wiringConfig;
    }

    public VisionProcessingConfig getVisionProcessingConfig() {
        return visionProcessingConfig;
    }

    public ControlLoopConfig getControlLoopConfig() {
        return controlLoopConfig;
    }

    public IntakeConfig getIntakeConfig() {
        return intakeConfig;
    }

    public ManipulatorConfig getManipulatorConfig() {
        return manipulatorConfig;
    }
}
