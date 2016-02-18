package org.usfirst.frc2175.config;

/**
 * Robot configuration values.
 */
public class RobotConfig {
    private final GamepadConfig gamepadConfig = new GamepadConfig();
    private final JoysticksConfig joystickConfig = new JoysticksConfig();
    private final WiringConfig wiringConfig = new WiringConfig();
    private final VisionProcessingConfig visionProcessingConfig =
            new VisionProcessingConfig();
    private final ControlLoopConfig controlLoopConfig = new ControlLoopConfig();
    private final IntakeConfig intakeConfig = new IntakeConfig();
    private final CatapultShooterConfig catapultShooterConfig =
            new CatapultShooterConfig();

    private final PowertrainConfig powertrainConfig = new PowertrainConfig();

    public PowertrainConfig getPowertrainConfig() {
        return powertrainConfig;
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
}
