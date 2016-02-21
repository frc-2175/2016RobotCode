package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WeakenMoatAutonomous extends CommandGroup {
    private double travelLength;

    public WeakenMoatAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        travelLength = robotSubsystems.getRobotConfig().getAutonomousConfig()
                .getTravelLength();
        // TO-DO Refine numbers if needed
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                travelLength));
    }
}
