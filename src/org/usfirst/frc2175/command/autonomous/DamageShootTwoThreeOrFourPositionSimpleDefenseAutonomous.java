package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.group.TurnToFaceGoalAndShootGroup;
import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.config.AutonomousConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DamageShootTwoThreeOrFourPositionSimpleDefenseAutonomous
        extends CommandGroup {
    public DamageShootTwoThreeOrFourPositionSimpleDefenseAutonomous(
            RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();
        double travelLength = autonomousConfig.getTravelLength();
        int caution = autonomousConfig.getCaution();
        double travelLengthWithCaution = travelLength - caution;

        // TODO refine distances
        // drive through obstacle
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                travelLength));
        // turn towards goal and shoot
        addSequential(new TurnToFaceGoalAndShootGroup(robotSubsystems,
                robotConfig, robotControllers));
        // turn parallel to obstacle
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 0, true));
        // drive through obstacle
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                -travelLength));
        // drive through obstacle
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                travelLengthWithCaution));
    }
}
