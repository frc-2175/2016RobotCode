package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.EmptyCommand;
import org.usfirst.frc2175.command.group.CatapultRampShotCommandGroup;
import org.usfirst.frc2175.command.group.RunIntakeInGroup;
import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.TurnToFaceGoalCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.config.AutonomousConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class WeakenShootLowBarAutonomous extends CommandGroup {

    public WeakenShootLowBarAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();
        double travelLength = autonomousConfig.getTravelLength();
        int extraShootLength = autonomousConfig.getExtraShootLength();
        int caution = autonomousConfig.getCaution();
        double distanceWithShoot =
                travelLength + extraShootLength - 2 * caution;

        // TODO Refine numbers if needed
        // TODO Refine angle
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                distanceWithShoot));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 20, false));
        // TODO make this take robotConfig
        addParallel(new RunIntakeInGroup(robotSubsystems,
                robotConfig.getIntakeConfig()), 10);
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, 24));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 45, false));
        addSequential(new EmptyCommand(), .4);
        addSequential(new TurnToFaceGoalCommand(robotSubsystems, robotConfig,
                robotControllers));
        addSequential(new EmptyCommand(), .4);
        addSequential(new CatapultRampShotCommandGroup(robotSubsystems));
    }
}
