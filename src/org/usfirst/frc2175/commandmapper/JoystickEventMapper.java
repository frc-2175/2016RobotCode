package org.usfirst.frc2175.commandmapper;

import org.usfirst.frc2175.command.group.CatapultShortShotCommandGroup;
import org.usfirst.frc2175.command.group.RunIntakeInGroup;
import org.usfirst.frc2175.command.group.RunIntakeOutGroup;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.RetractCatapultCommand;
import org.usfirst.frc2175.command.single.RunBootAtSpeedCommand;
import org.usfirst.frc2175.command.single.RunIntakeLiftAtSpeedCommand;
import org.usfirst.frc2175.command.single.ShiftToClimbGearCommand;
import org.usfirst.frc2175.command.single.ShiftToHighGearCommand;
import org.usfirst.frc2175.command.single.TurnToFaceGoalCommand;
import org.usfirst.frc2175.config.GamepadConfig;
import org.usfirst.frc2175.config.IntakeConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.driverstation.DriverStation;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Map {@link Command}s to joystick buttons.
 */
public class JoystickEventMapper {
    private double bootSpeed;
    private double liftIntakeSpeed;

    public JoystickEventMapper(RobotConfig robotConfig,
            DriverStation driverStation, RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        GamepadConfig gamepadConfig = robotConfig.getGamepadConfig();
        IntakeConfig intakeConfig = robotConfig.getIntakeConfig();

        JoystickButton extendCatapult = gamepadConfig.getExtendCatapult();
        extendCatapult.whenPressed(new ExtendCatapultCommand(robotSubsystems));

        JoystickButton retractCatapult = gamepadConfig.getRetractCatapult();
        retractCatapult
                .whenPressed(new RetractCatapultCommand(robotSubsystems));

        JoystickButton shortShot = gamepadConfig.getShortShot();
        shortShot.whenPressed(
                new CatapultShortShotCommandGroup(robotSubsystems));

        JoystickButton runIntakeIn = gamepadConfig.getRunIntakeIn();
        runIntakeIn
                .whileHeld(new RunIntakeInGroup(robotSubsystems, intakeConfig));

        JoystickButton runIntakeOut = gamepadConfig.getRunIntakeOut();
        runIntakeOut.whileHeld(
                new RunIntakeOutGroup(robotSubsystems, intakeConfig));

        JoystickButton testAction = gamepadConfig.getTestAction();
        testAction.whenPressed(
                new TurnToFaceGoalCommand(robotSubsystems, robotControllers));

        JoystickButton upshift =
                robotConfig.getJoysticksConfig().getUpshiftButton();
        upshift.whileHeld(new ShiftToHighGearCommand(robotSubsystems));

        bootSpeed = robotConfig.getManipulatorConfig().getBootSpeed();
        JoystickButton climbshift =
                robotConfig.getJoysticksConfig().getClimbshiftButton();
        climbshift.toggleWhenPressed(
                new ShiftToClimbGearCommand(robotSubsystems));

        JoystickButton lowerBoot =
                robotConfig.getGamepadConfig().getLowerBoot();
        lowerBoot.whileHeld(
                new RunBootAtSpeedCommand(robotSubsystems, bootSpeed));
        JoystickButton raiseBoot =
                robotConfig.getGamepadConfig().getRaiseBoot();
        raiseBoot.whileHeld(
                new RunBootAtSpeedCommand(robotSubsystems, -bootSpeed));

        liftIntakeSpeed = robotConfig.getIntakeConfig().getLiftIntakeSpeed();
        JoystickButton raiseIntake =
                robotConfig.getGamepadConfig().getRaiseIntake();
        raiseIntake.whileHeld(new RunIntakeLiftAtSpeedCommand(robotSubsystems,
                liftIntakeSpeed));

        JoystickButton lowerIntake =
                robotConfig.getGamepadConfig().getLowerIntake();
        lowerIntake.whileHeld(new RunIntakeLiftAtSpeedCommand(robotSubsystems,
                -liftIntakeSpeed));
    }
}
