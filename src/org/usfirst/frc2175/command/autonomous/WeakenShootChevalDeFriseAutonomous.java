package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.group.TurnToFaceGoalAndShootGroup;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WeakenShootChevalDeFriseAutonomous extends CommandGroup {

    public WeakenShootChevalDeFriseAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();

        // TODO refine numbers
        // drive over cheval
        addSequential(new WeakenChevalDeFriseAutonomous(robotSubsystems,
                robotControllers));
        // turn to goal and shoot
        addSequential(new TurnToFaceGoalAndShootGroup(robotSubsystems,
                robotConfig, robotControllers));
    }
}
