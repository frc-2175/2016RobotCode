package org.usfirst.frc2175.command.autonomous.block;

import org.usfirst.frc2175.command.EmptyCommand;
import org.usfirst.frc2175.command.single.TurnToFaceGoalWithGyroCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TurnToCenterOfGoalBlock extends CommandGroup {
    public TurnToCenterOfGoalBlock(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers, int times) {
        for (int i = 0; i < times; i++) {
            addSequential(new TurnToFaceGoalWithGyroCommand(robotSubsystems,
                    robotControllers));
            addSequential(new EmptyCommand(), (times - (i * 3 / 4)) / 10);
        }
    }
}
