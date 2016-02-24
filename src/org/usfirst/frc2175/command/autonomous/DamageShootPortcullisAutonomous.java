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

public class DamageShootPortcullisAutonomous extends CommandGroup {

    public DamageShootPortcullisAutonomous(RobotSubsystems robotSubsystems,
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
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                platformBeforePortcullis));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(new DriveInchesCommand(robotSubsystems, robotControllers,
                distanceAfterPortcullis));
        addSequential(new LowerBootCommand(robotSubsystems));
        addSequential(new ExtendCatapultCommand(robotSubsystems));
        addSequential(new RetractCatapultCommand(robotSubsystems));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, turnAround, true));
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                platformBeforePortcullis));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(new DriveInchesCommand(robotSubsystems, robotControllers,
                distanceAfterPortcullis));
        addSequential(new LowerBootCommand(robotSubsystems));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, turnAround, true));
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                platformBeforePortcullis));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(new DriveInchesCommand(robotSubsystems, robotControllers,
                distanceAfterPortcullisWithCaution));
        addSequential(new RaiseBootCommand(robotSubsystems));
    }
}
