package org.usfirst.frc2175.commandmapper;

import java.util.logging.Logger;

import org.usfirst.frc2175.command.group.CatapultShootGroup;
import org.usfirst.frc2175.command.group.RunIntakeInGroup;
import org.usfirst.frc2175.command.group.RunIntakeOutGroup;
import org.usfirst.frc2175.command.single.CycleDesiredShotCommand;
import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.RetractCatapultCommand;
import org.usfirst.frc2175.command.single.RunBootAtSpeedCommand;
import org.usfirst.frc2175.command.single.RunIntakeLiftAtSpeedCommand;
import org.usfirst.frc2175.command.single.ShiftToClimbGearLowCommand;
import org.usfirst.frc2175.command.single.ShiftToHighGearCommand;
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
    private final Logger log = Logger.getLogger(getClass().getName());

    private double bootSpeed;
    private double liftIntakeSpeed;

    public JoystickEventMapper(RobotConfig robotConfig,
            DriverStation driverStation, RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        log.info("Configuring class=" + getClass());

        GamepadConfig gamepadConfig = robotConfig.getGamepadConfig();
        IntakeConfig intakeConfig = robotConfig.getIntakeConfig();

        JoystickButton extendCatapult = gamepadConfig.getExtendCatapult();
        extendCatapult.whenPressed(new CatapultShootGroup(robotSubsystems));

        JoystickButton retractCatapult = gamepadConfig.getRetractCatapult();
        retractCatapult
                .whenPressed(new RetractCatapultCommand(robotSubsystems));

        JoystickButton cycleShot = gamepadConfig.getCycleShot();
        cycleShot.whenPressed(new CycleDesiredShotCommand(robotSubsystems));

        JoystickButton runIntakeIn = gamepadConfig.getRunIntakeIn();
        runIntakeIn
                .whileHeld(new RunIntakeInGroup(robotSubsystems, intakeConfig));

        JoystickButton runIntakeOut = gamepadConfig.getRunIntakeOut();
        runIntakeOut.whileHeld(
                new RunIntakeOutGroup(robotSubsystems, intakeConfig));

        JoystickButton testAction = gamepadConfig.getTestAction();
        testAction.whenPressed(
                new DriveInchesCommand(robotSubsystems, robotControllers, 90));

        JoystickButton upshift =
                robotConfig.getJoysticksConfig().getUpshiftButton();
        upshift.whileHeld(new ShiftToHighGearCommand(robotSubsystems));

        bootSpeed = robotConfig.getManipulatorConfig().getBootSpeed();

        JoystickButton climbshift =
                robotConfig.getJoysticksConfig().getClimbshiftButton();
        climbshift.toggleWhenPressed(
                new ShiftToClimbGearLowCommand(robotSubsystems));

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
