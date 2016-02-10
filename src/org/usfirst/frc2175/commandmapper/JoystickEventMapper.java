package org.usfirst.frc2175.commandmapper;

import org.usfirst.frc2175.command.group.RunIntakeInGroup;
import org.usfirst.frc2175.command.group.RunIntakeOutGroup;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.RetractCatapultCommand;
import org.usfirst.frc2175.command.single.ShiftToHighGearCommand;
import org.usfirst.frc2175.command.single.TurnToFaceGoalCommand;
import org.usfirst.frc2175.config.GamepadConfig;
import org.usfirst.frc2175.config.IntakeConfig;
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
        IntakeConfig intakeConfig = robotConfig.getIntakeConfig();

        JoystickButton extendCatapult = gamepadConfig.getExtendCatapultButton();
        extendCatapult.whenPressed(new ExtendCatapultCommand(robotSubsystems));

        JoystickButton retractCatapult =
                gamepadConfig.getRetractCatapultButton();
        retractCatapult
                .whenPressed(new RetractCatapultCommand(robotSubsystems));

        JoystickButton runIntakeIn = gamepadConfig.getIntakeInButton();
        runIntakeIn.whenPressed(
                new RunIntakeInGroup(robotSubsystems, intakeConfig));

        JoystickButton runIntakeOut = gamepadConfig.getIntakeOutButton();
        runIntakeOut.whenPressed(
                new RunIntakeOutGroup(robotSubsystems, intakeConfig));

        JoystickButton testAction = gamepadConfig.getTestActionButton();
        testAction.whenPressed(new TurnToFaceGoalCommand(robotSubsystems));

        JoystickButton upshift = gamepadConfig.getUpshiftButton();
        upshift.whenPressed(new ShiftToHighGearCommand(robotSubsystems));
    }
}
