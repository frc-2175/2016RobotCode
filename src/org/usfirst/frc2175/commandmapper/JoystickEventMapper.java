package org.usfirst.frc2175.commandmapper;

import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.RetractCatapultCommand;
import org.usfirst.frc2175.command.single.TurnToFaceGoalCommand;
import org.usfirst.frc2175.config.GamepadConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.driverstation.DriverStation;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Map {@link Command}s to joystick buttons.
 */
public class JoystickEventMapper {
    public JoystickEventMapper(RobotConfig robotConfig,
            DriverStation driverStation, RobotSubsystems robotSubsystems) {
        GamepadConfig gamepadConfig = robotConfig.getGamepadConfig();

        JoystickButton extendCatapult = gamepadConfig.getExtendCatapultButton();
        extendCatapult.whenPressed(new ExtendCatapultCommand(robotSubsystems));

        JoystickButton retractCatapult =
                gamepadConfig.getRetractCatapultButton();
        retractCatapult
                .whenPressed(new RetractCatapultCommand(robotSubsystems));

        JoystickButton testAction = gamepadConfig.getTestActionButton();
        testAction.whenPressed(new TurnToFaceGoalCommand(robotSubsystems));
    }
}
