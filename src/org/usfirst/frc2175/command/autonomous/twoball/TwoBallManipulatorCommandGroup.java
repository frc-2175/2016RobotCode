package org.usfirst.frc2175.command.autonomous.twoball;

import org.usfirst.frc2175.command.EmptyCommand;
import org.usfirst.frc2175.command.group.CatapultShootGroup;
import org.usfirst.frc2175.command.group.RunIntakeInGroup;
import org.usfirst.frc2175.command.single.RunIntakeLiftAtSpeedCommand;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoBallManipulatorCommandGroup extends CommandGroup {

    public TwoBallManipulatorCommandGroup(RobotConfig robotConfig,
            RobotSubsystems robotSubsystems) {

        /*
         * All commands throughout the two halves of the auton should be added
         * in parallel, with sequential EmptyCommands to enforce timing between
         * them. This will allow us to keep the two halves of the loop in sync.
         */

        /*-------------BEGIN ROUTINE HERE---------------------*/
        // Wait to open intake (2.5 sec)
        addSequential(new EmptyCommand(), 2.5);
        // Open intake (0.8 seconds, totaling 3.3)
        addParallel(new RunIntakeLiftAtSpeedCommand(robotSubsystems, 1), .8);
        addSequential(new EmptyCommand(), .8);
        // Wait for aim (4 seconds, totaling 7.3)
        addSequential(new EmptyCommand(), 4);
        // Shoot (.7 seconds, totaling 8)
        addParallel(new CatapultShootGroup(robotSubsystems));
        addSequential(new EmptyCommand(), .7);
        // Wait to cross defense (4 seconds, totaling 12)
        addSequential(new EmptyCommand(), 4);
        // Intake ball (run for 2 seconds to be safe, totaling 14)
        addSequential(new RunIntakeInGroup(robotSubsystems,
                robotConfig.getIntakeConfig()), 2);
        addParallel(new EmptyCommand(), 2);
        // TODO finish this

    }

}
