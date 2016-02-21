package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.config.AutonomousConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WeakenChevalDeFriseAutonomous extends CommandGroup {

    public WeakenChevalDeFriseAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();
        double travelLength = autonomousConfig.getTravelLength();
        int caution = autonomousConfig.getCaution();
        int platformBeforeCheval = autonomousConfig.getPlatformBeforeCheval();
        double distanceAfterCheval = travelLength - platformBeforeCheval;

        // TODO Refine numbers if needed
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                platformBeforeCheval));
        addSequential(new LowerBootCommand(robotSubsystems));
        addParallel(new DriveInches(robotSubsystems, robotControllers,
                distanceAfterCheval));
        addSequential(new RaiseBootCommand(robotSubsystems));

    }
}
