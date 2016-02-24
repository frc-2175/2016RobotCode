package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.ExtendClimberCommand;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.LowerClimberCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.command.single.RaiseClimberCommand;
import org.usfirst.frc2175.command.single.RetractCatapultCommand;
import org.usfirst.frc2175.command.single.RetractClimberCommand;
import org.usfirst.frc2175.command.single.RunRollerbarIntakeAtSpeedCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestAllFeaturesAutonomous extends CommandGroup {
    public TestAllFeaturesAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 90, true));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, -180, false));
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, 8));
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, -8));
        addSequential(new RunRollerbarIntakeAtSpeedCommand(robotSubsystems, 1));
        addSequential(
                new RunRollerbarIntakeAtSpeedCommand(robotSubsystems, -1));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addSequential(new LowerBootCommand(robotSubsystems));
        addSequential(new ExtendCatapultCommand(robotSubsystems));
        addSequential(new RetractCatapultCommand(robotSubsystems));
        addSequential(new RaiseClimberCommand(robotSubsystems));
        addSequential(new ExtendClimberCommand(robotSubsystems));
        addSequential(new RetractClimberCommand(robotSubsystems));
        addSequential(new LowerClimberCommand(robotSubsystems));

    }
}
