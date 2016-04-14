package org.usfirst.frc2175.command.autonomous.block;

import org.usfirst.frc2175.command.EmptyCommand;
import org.usfirst.frc2175.command.single.TurnToFaceGoalWithGyroCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TurnToCenterOfGoalBlock extends CommandGroup {
    public TurnToCenterOfGoalBlock(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers,
            VisionProcessing visionProcessing, int times) {
        for (int i = 0; i < times; i++) {
            addSequential(new TurnToFaceGoalWithGyroCommand(robotSubsystems,
                    robotControllers, visionProcessing));
            addSequential(new EmptyCommand(), (times - (i * 3 / 4)) / 10);
        }
    }
}
