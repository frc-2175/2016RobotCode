package org.usfirst.frc2175.commandmapper;

import org.usfirst.frc2175.command.group.ExtendCatapultFullPowerGroup;
import org.usfirst.frc2175.command.group.ExtendCatapultMiddleOnGroup;
import org.usfirst.frc2175.command.group.ExtendCatapultSidesOnlyGroup;
import org.usfirst.frc2175.command.group.RetractCatapultGroup;
import org.usfirst.frc2175.command.group.RunIntakeInGroup;
import org.usfirst.frc2175.command.group.RunIntakeOutGroup;
import org.usfirst.frc2175.command.single.RunBootAtSpeedCommand;
import org.usfirst.frc2175.command.single.RunIntakeLiftAtSpeedCommand;
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

        JoystickButton extendCatapult =
                gamepadConfig.getExtendCatapultFullPower();
        extendCatapult
                .whenPressed(new ExtendCatapultFullPowerGroup(robotSubsystems));

        JoystickButton extendCatapultMiddleOn =
                gamepadConfig.getExtendCatapultMiddleOn();
        extendCatapultMiddleOn
                .whenPressed(new ExtendCatapultMiddleOnGroup(robotSubsystems));

        JoystickButton extendCatapultSidesOnly =
                gamepadConfig.getExtendCatapultSidesOnly();
        extendCatapultSidesOnly
                .whenPressed(new ExtendCatapultSidesOnlyGroup(robotSubsystems));

        JoystickButton retractCatapult = gamepadConfig.getRetractCatapult();
        retractCatapult.whenPressed(new RetractCatapultGroup(robotSubsystems));

        JoystickButton runIntakeIn = gamepadConfig.getRunIntakeIn();
        runIntakeIn
                .whileHeld(new RunIntakeInGroup(robotSubsystems, intakeConfig));

        JoystickButton runIntakeOut = gamepadConfig.getRunIntakeOut();
        runIntakeOut.whileHeld(
                new RunIntakeOutGroup(robotSubsystems, intakeConfig));

        JoystickButton testAction = gamepadConfig.getTestAction();
        testAction.whenPressed(new TurnToFaceGoalCommand(robotSubsystems));

        JoystickButton upshift =
                robotConfig.getJoysticksConfig().getUpshiftButton();
        upshift.whileHeld(new ShiftToHighGearCommand(robotSubsystems));

        JoystickButton lowerBoot =
                robotConfig.getGamepadConfig().getLowerBoot();
        lowerBoot.whileHeld(new RunBootAtSpeedCommand(robotSubsystems, .8));

        JoystickButton raiseBoot =
                robotConfig.getGamepadConfig().getRaiseBoot();
        raiseBoot.whileHeld(new RunBootAtSpeedCommand(robotSubsystems, -.8));

        JoystickButton raiseIntake =
                robotConfig.getGamepadConfig().getRaiseIntake();
        raiseIntake.whileHeld(
                new RunIntakeLiftAtSpeedCommand(robotSubsystems, .5));

        JoystickButton lowerIntake =
                robotConfig.getGamepadConfig().getLowerIntake();
        lowerIntake.whileHeld(
                new RunIntakeLiftAtSpeedCommand(robotSubsystems, -.5));

    }
}
