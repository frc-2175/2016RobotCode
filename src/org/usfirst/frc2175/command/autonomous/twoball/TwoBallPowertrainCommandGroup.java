package org.usfirst.frc2175.command.autonomous.twoball;

import org.usfirst.frc2175.command.EmptyCommand;
import org.usfirst.frc2175.command.autonomous.block.CrossStaticDefenseBlock;
import org.usfirst.frc2175.command.autonomous.block.TurnToCenterOfGoalBlock;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoBallPowertrainCommandGroup extends CommandGroup {

    public TwoBallPowertrainCommandGroup(RobotConfig robotConfig,
            RobotSubsystems robotSubsystems, RobotControllers robotControllers,
            VisionProcessing visionProcessing) {

        /**
         * All commands throughout the two halves of the auton should be added
         * in parallel, with sequential EmptyCommands to enforce timing between
         * them. This will allow us to keep the two halves of the loop in sync.
         */
        /*----------------------BEGIN ROUTINE HERE---------------------*/
        // Drive forward (up to 4 seconds)
        addParallel(new CrossStaticDefenseBlock(robotSubsystems,
                robotControllers, true));
        addSequential(new EmptyCommand(), 4);
        // Aim (up to 2 seconds, bringing it to 6)
        addParallel(new TurnToCenterOfGoalBlock(robotSubsystems,
                robotControllers, visionProcessing, 3));
        addSequential(new EmptyCommand(), 2);
        // Turn back (Up to 2 seconds, bringing it to 8)
        addParallel(
                new TurnToHeadingCommand(robotSubsystems, robotControllers, 0));
        addSequential(new EmptyCommand(), 2);
        // Drive back to pick up ball (Up to 4 seconds, bringing it to 12)
        addParallel(new CrossStaticDefenseBlock(robotSubsystems,
                robotControllers, false));
        addSequential(new EmptyCommand(), 2);
        // Drive forward for second cross (Up to 4 seconds, bringing it to 16)
        addParallel(new CrossStaticDefenseBlock(robotSubsystems,
                robotControllers, true));
        addSequential(new EmptyCommand(), 4);
        // Aim (Until auton ends)
        addParallel(new TurnToCenterOfGoalBlock(robotSubsystems,
                robotControllers, visionProcessing, 3));

    }

}
