package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.RetractCatapultCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.config.AutonomousConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DamageShootRampartsAutonomous extends CommandGroup {

    public DamageShootRampartsAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();

        double travelLength = autonomousConfig.getTravelLength();
        int caution = autonomousConfig.getCaution();
        int extraShootLength = autonomousConfig.getExtraShootLength();
        double distanceWithShoot = travelLength + extraShootLength;
        double distanceWithCaution = travelLength - caution;

        // TODO Refine Numbers if needed
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                distanceWithShoot));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 30, true));
        addSequential(new ExtendCatapultCommand(robotSubsystems));
        addParallel(new RetractCatapultCommand(robotSubsystems));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 0, false));
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                -distanceWithShoot));
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                distanceWithCaution));
    }
}
