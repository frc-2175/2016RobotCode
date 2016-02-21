package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WeakenChevalDeFriseAutonomous extends CommandGroup {
    private double travelLength;
    private int platformBeforeCheval;

    public WeakenChevalDeFriseAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        travelLength = robotSubsystems.getRobotConfig().getAutonomousConfig()
                .getTravelLength();
        platformBeforeCheval = robotSubsystems.getRobotConfig()
                .getAutonomousConfig().getPlatformBeforeCheval();
        // TODO Refine numbers if needed
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                platformBeforeCheval));
        addSequential(new LowerBootCommand(robotSubsystems));
        addParallel(new DriveInches(robotSubsystems, robotControllers,
                travelLength - platformBeforeCheval));
        addSequential(new RaiseBootCommand(robotSubsystems));

    }
}
