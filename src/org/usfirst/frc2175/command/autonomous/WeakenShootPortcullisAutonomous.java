package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.group.TurnToFaceGoalAndShootGroup;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WeakenShootPortcullisAutonomous extends CommandGroup {

    public WeakenShootPortcullisAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();

        // TODO refine numbers
        // open portcullis and drive through
        addSequential(new WeakenPortcullisAutonomous(robotSubsystems,
                robotControllers));
        // turn to goal and shoot
        addSequential(new TurnToFaceGoalAndShootGroup(robotSubsystems,
                robotConfig, robotControllers));
    }

}
