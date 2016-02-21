package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.config.AutonomousConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DamagePortcullisAutonomous extends CommandGroup {

    public DamagePortcullisAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();
        double travelLength = autonomousConfig.getTravelLength();
        int caution = autonomousConfig.getCaution();
        int platformBeforePortcullis =
                autonomousConfig.getPlatformBeforePortcullis();
        int turnAround = autonomousConfig.getTurnAround();
        double distanceAfterPortcullis =
                travelLength - platformBeforePortcullis;
        double distanceAfterPortcullisWithCaution =
                distanceAfterPortcullis - caution;

        // TODO Refine numbers if needed
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                platformBeforePortcullis));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(new DriveInches(robotSubsystems, robotControllers,
                distanceAfterPortcullis));
        addSequential(new LowerBootCommand(robotSubsystems));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, turnAround, true));
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                platformBeforePortcullis));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(new DriveInches(robotSubsystems, robotControllers,
                distanceAfterPortcullis));
        addSequential(new LowerBootCommand(robotSubsystems));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, turnAround, true));
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                platformBeforePortcullis));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(new DriveInches(robotSubsystems, robotControllers,
                distanceAfterPortcullisWithCaution));
        addSequential(new RaiseBootCommand(robotSubsystems));
    }

}
