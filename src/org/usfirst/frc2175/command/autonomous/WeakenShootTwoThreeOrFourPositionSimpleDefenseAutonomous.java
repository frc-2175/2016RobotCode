package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.group.TurnToFaceGoalAndShootGroup;
import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.config.AutonomousConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WeakenShootTwoThreeOrFourPositionSimpleDefenseAutonomous
        extends CommandGroup {
    public WeakenShootTwoThreeOrFourPositionSimpleDefenseAutonomous(
            RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();
        double travelLength = autonomousConfig.getTravelLength();

        // TODO refine distances
        // drive through obstacle
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                travelLength));
        // turn towards goal and shoot
        addSequential(new TurnToFaceGoalAndShootGroup(robotSubsystems,
                robotConfig, robotControllers));
    }

}