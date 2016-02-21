package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DamageSimpleDefenseAutonomous extends CommandGroup {
    private double travelLength;
    private int caution;

    public DamageSimpleDefenseAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        travelLength = robotSubsystems.getRobotConfig().getAutonomousConfig()
                .getTravelLength();
        caution = robotSubsystems.getRobotConfig().getAutonomousConfig()
                .getCaution();
        // TO-DO Fix numbers as needed
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                travelLength));
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                -travelLength));
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                travelLength - caution));
    }
}
