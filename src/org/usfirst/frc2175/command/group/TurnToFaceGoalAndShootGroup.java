package org.usfirst.frc2175.command.group;

import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.TurnToFaceGoalCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TurnToFaceGoalAndShootGroup extends CommandGroup {

    public TurnToFaceGoalAndShootGroup(RobotSubsystems robotSubsystems) {

        addSequential(new TurnToFaceGoalCommand(robotSubsystems), 10);
        addSequential(new ExtendCatapultCommand(robotSubsystems));

    }
}
