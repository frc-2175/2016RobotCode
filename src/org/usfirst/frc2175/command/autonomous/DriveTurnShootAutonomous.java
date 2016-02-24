package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.config.AutonomousConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveTurnShootAutonomous extends CommandGroup {
    public DriveTurnShootAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();
        double travelLength = autonomousConfig.getTravelLength();
        int distanceAfterTurn = 0;
        int specifiedDegrees = 0;

        robotSubsystems.getPowertrainSubsystem().resetGyro();
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                travelLength));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 0, true));
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                distanceAfterTurn));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, specifiedDegrees, true));
        // TODO Add auto-aim here
        addSequential(new ExtendCatapultCommand(robotSubsystems));

    }

}
