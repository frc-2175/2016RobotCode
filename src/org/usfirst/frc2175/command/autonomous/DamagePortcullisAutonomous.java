package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DamagePortcullisAutonomous extends CommandGroup {
    private double travelLength;
    private int platformBeforePortcullis;
    private int caution;

    public DamagePortcullisAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        travelLength = robotSubsystems.getRobotConfig().getAutonomousConfig()
                .getTravelLength();
        caution = robotSubsystems.getRobotConfig().getAutonomousConfig()
                .getCaution();
        platformBeforePortcullis = robotSubsystems.getRobotConfig()
                .getAutonomousConfig().getPlatformBeforePortcullis();
        // TODO Refine numbers if needed
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                platformBeforePortcullis));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(new DriveInches(robotSubsystems, robotControllers,
                travelLength - platformBeforePortcullis));
        addSequential(new LowerBootCommand(robotSubsystems));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 180, true));
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                platformBeforePortcullis));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(new DriveInches(robotSubsystems, robotControllers,
                travelLength - platformBeforePortcullis));
        addSequential(new LowerBootCommand(robotSubsystems));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 180, true));
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                platformBeforePortcullis));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(new DriveInches(robotSubsystems, robotControllers,
                travelLength - platformBeforePortcullis - caution));
        addSequential(new RaiseBootCommand(robotSubsystems));
    }

}
