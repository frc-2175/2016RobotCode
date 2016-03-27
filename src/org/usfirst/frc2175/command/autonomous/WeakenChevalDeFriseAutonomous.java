package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInchesCommand;
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
        int platformBeforeCheval = autonomousConfig.getPlatformBeforeCheval();
        double distanceAfterCheval = travelLength - platformBeforeCheval;

        // TODO refine numbers
        // drive up to cheval's moving plates
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                platformBeforeCheval));
        // lower boot to lower the plates
        addSequential(new LowerBootCommand(robotSubsystems));
        // drive off of the obstacle while raising the boot
        addParallel(new DriveInchesCommand(robotSubsystems, robotControllers,
                distanceAfterCheval));
        addSequential(new RaiseBootCommand(robotSubsystems));

    }
}
