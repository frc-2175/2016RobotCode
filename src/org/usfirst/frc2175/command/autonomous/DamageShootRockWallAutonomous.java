package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.RetractCatapultCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DamageShootRockWallAutonomous extends CommandGroup {
    private double travelLength;
    private int caution;
    private int extraShootLength;

    public DamageShootRockWallAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        travelLength = robotSubsystems.getRobotConfig().getAutonomousConfig()
                .getTravelLength();
        caution = robotSubsystems.getRobotConfig().getAutonomousConfig()
                .getCaution();
        extraShootLength = robotSubsystems.getRobotConfig()
                .getAutonomousConfig().getExtraShootLength();
        // TODO Refine Numbers if needed
        // TODO Change angle of turn
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                travelLength + extraShootLength));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 30, true));
        addSequential(new ExtendCatapultCommand(robotSubsystems));
        addParallel(new RetractCatapultCommand(robotSubsystems));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 0, false));
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                -(travelLength + extraShootLength)));
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                travelLength - caution));
    }
}
