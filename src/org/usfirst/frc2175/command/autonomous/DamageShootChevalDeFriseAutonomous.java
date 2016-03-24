package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.command.single.RetractCatapultCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.config.AutonomousConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DamageShootChevalDeFriseAutonomous extends CommandGroup {

    public DamageShootChevalDeFriseAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();
        double travelLength = autonomousConfig.getTravelLength();
        int caution = autonomousConfig.getCaution();
        int extraShootLength = autonomousConfig.getExtraShootLength();
        int platformBeforeCheval = autonomousConfig.getPlatformBeforeCheval();
        int turnAround = autonomousConfig.getTurnAround();
        double distanceWithShoot = travelLength + extraShootLength;
        double distanceAfterChevalWithShoot =
                travelLength - platformBeforeCheval + extraShootLength;
        double distanceAfterCheval = travelLength - platformBeforeCheval;
        double distanceAfterChevalWithCaution = distanceAfterCheval - caution;

        // TODO Refine Numbers if needed
        // TODO Refine Angle
        // TODO add descriptive comments for each command
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                platformBeforeCheval));
        addSequential(new LowerBootCommand(robotSubsystems));
        addParallel(new RaiseBootCommand(robotSubsystems));
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                distanceAfterChevalWithShoot));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 30, true));
        addSequential(new ExtendCatapultCommand(robotSubsystems));
        addParallel(new RetractCatapultCommand(robotSubsystems));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 0, false));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, turnAround, true));
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                -distanceWithShoot));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, turnAround, false));
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                platformBeforeCheval));
        addSequential(new LowerBootCommand(robotSubsystems));
        addParallel(new RaiseBootCommand(robotSubsystems));
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                distanceAfterChevalWithCaution));
    }
}
