package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DamageChevalDeFriseAutonomous extends CommandGroup {
    private double travelLength;
    private int caution;
    private int platformBeforeCheval;

    public DamageChevalDeFriseAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        travelLength = robotSubsystems.getRobotConfig().getAutonomousConfig()
                .getTravelLength();
        caution = robotSubsystems.getRobotConfig().getAutonomousConfig()
                .getCaution();
        platformBeforeCheval = robotSubsystems.getRobotConfig()
                .getAutonomousConfig().getPlatformBeforeCheval();

        addSequential(new DriveInches(robotSubsystems, robotControllers,
                platformBeforeCheval));
        addSequential(new LowerBootCommand(robotSubsystems));
        addParallel(new DriveInches(robotSubsystems, robotControllers,
                travelLength - platformBeforeCheval));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 180, true));
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                platformBeforeCheval));
        addSequential(new LowerBootCommand(robotSubsystems));
        addParallel(new DriveInches(robotSubsystems, robotControllers,
                travelLength - platformBeforeCheval));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 180, true));
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                platformBeforeCheval));
        addSequential(new LowerBootCommand(robotSubsystems));
        addParallel(new DriveInches(robotSubsystems, robotControllers,
                travelLength - platformBeforeCheval - caution));
        addSequential(new RaiseBootCommand(robotSubsystems));

    }
}
