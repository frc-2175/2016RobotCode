package org.usfirst.frc2175.command.group;

import org.usfirst.frc2175.command.EmptyCommand;
import org.usfirst.frc2175.command.single.TurnToFaceGoalCommand;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TurnToFaceGoalAndShootGroup extends CommandGroup {

    public TurnToFaceGoalAndShootGroup(RobotSubsystems robotSubsystems,
            RobotConfig robotConfig, RobotControllers robotControllers) {

        addSequential(new TurnToFaceGoalCommand(robotSubsystems, robotConfig,
                robotControllers));
        addSequential(new EmptyCommand(), .2);
        addSequential(new CatapultShootGroup(robotSubsystems));

    }
}
