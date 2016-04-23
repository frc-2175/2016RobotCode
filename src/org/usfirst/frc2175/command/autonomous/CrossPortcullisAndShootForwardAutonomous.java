package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.autonomous.block.CrossPortcullisBlock;
import org.usfirst.frc2175.command.autonomous.block.TurnToCenterOfGoalBlock;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossPortcullisAndShootForwardAutonomous extends CommandGroup {
    public CrossPortcullisAndShootForwardAutonomous(
            RobotSubsystems robotSubsystems, RobotControllers robotControllers,
            VisionProcessing visionProcessing) {
        // crosses portcullis
        addSequential(new CrossPortcullisBlock(robotSubsystems,
                robotControllers, false));
        // aligns to shoot
        addSequential(new TurnToCenterOfGoalBlock(robotSubsystems,
                robotControllers, visionProcessing, 5));
        // shoot
        addSequential(new ExtendCatapultCommand(robotSubsystems));
    }
}
