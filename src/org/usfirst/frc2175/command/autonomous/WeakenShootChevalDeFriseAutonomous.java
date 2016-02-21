package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WeakenShootChevalDeFriseAutonomous extends CommandGroup {
    private double travelLength;
    private int extraShootLength;
    private int platformBeforeCheval;

    public WeakenShootChevalDeFriseAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        travelLength = robotSubsystems.getRobotConfig().getAutonomousConfig()
                .getTravelLength();
        extraShootLength = robotSubsystems.getRobotConfig()
                .getAutonomousConfig().getExtraShootLength();
        platformBeforeCheval = robotSubsystems.getRobotConfig()
                .getAutonomousConfig().getPlatformBeforeCheval();
        // TODO Refine Numbers if needed
        // TODO Refine Angle
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                platformBeforeCheval));
        addSequential(new LowerBootCommand(robotSubsystems));
        addParallel(new RaiseBootCommand(robotSubsystems));
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                travelLength - platformBeforeCheval + extraShootLength));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 30, true));
        addSequential(new ExtendCatapultCommand(robotSubsystems));
    }
}
