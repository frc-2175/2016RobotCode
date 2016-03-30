package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.TurnToFaceGoalCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.config.AutonomousConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveTurnShootAutonomous extends CommandGroup {
    public DriveTurnShootAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        PowertrainSubsystem powertrainSubsystem =
                robotSubsystems.getPowertrainSubsystem();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();
        double travelLength = autonomousConfig.getTravelLength();
        int distanceAfterTurn = 0;
        int specifiedDegrees = 0;

        // gyro zeroes at start
        robotSubsystems.getPowertrainSubsystem().resetGyro();
        // drive distance to cross over defense

        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                travelLength));
        // turn to gyro heading 0
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 0, true));
        // drive distance after turn
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                distanceAfterTurn));
        // turn a specified amount of degrees
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, specifiedDegrees, true));
        // use auto-aim and face the goal
        addSequential(new TurnToFaceGoalCommand(robotSubsystems, robotConfig,
                robotControllers));
        // extend catapult to shoot
        addSequential(new ExtendCatapultCommand(robotSubsystems));

    }

}
