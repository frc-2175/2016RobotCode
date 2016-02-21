package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.config.AutonomousConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WeakenShootChevalDeFriseAutonomous extends CommandGroup {

    public WeakenShootChevalDeFriseAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();
        double travelLength = autonomousConfig.getTravelLength();
        int extraShootLength = autonomousConfig.getExtraShootLength();
        int platformBeforeCheval = autonomousConfig.getPlatformBeforeCheval();
        double distanceAfterChevalWithShoot =
                travelLength - platformBeforeCheval + extraShootLength;

        // TODO Refine Numbers if needed
        // TODO Refine Angle
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                platformBeforeCheval));
        addSequential(new LowerBootCommand(robotSubsystems));
        addParallel(new RaiseBootCommand(robotSubsystems));
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                distanceAfterChevalWithShoot));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 30, true));
        addSequential(new ExtendCatapultCommand(robotSubsystems));
    }
}
