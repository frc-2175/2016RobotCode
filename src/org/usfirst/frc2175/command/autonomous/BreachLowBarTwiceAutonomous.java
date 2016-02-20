package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachLowBarTwiceAutonomous {

    public class BreachPortcullisAutonomous extends CommandGroup {

        public BreachPortcullisAutonomous(RobotSubsystems robotSubsystems,
                RobotControllers robotControllers) {
            // TO-DO Fix number of inches as needed
            addSequential(new DriveInches(robotSubsystems, robotControllers, 24));
            addSequential(new DriveInches(robotSubsystems, robotControllers, -24));
            addSequential(new DriveInches(robotSubsystems, robotControllers, 24));
        }
    }
}
