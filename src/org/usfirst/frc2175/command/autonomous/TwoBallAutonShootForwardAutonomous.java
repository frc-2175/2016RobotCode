package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoBallAutonShootForwardAutonomous extends CommandGroup {
    public TwoBallAutonShootForwardAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        addSequential(new WeakenSimpleDefenseAndShootForwardAutonomous(
                robotSubsystems, robotControllers));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 0, true));
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                -148));
        robotSubsystems.getPowertrainSubsystem().resetGyro();
        addSequential(new WeakenSimpleDefenseAndShootForwardAutonomous(
                robotSubsystems, robotControllers));
    }
}
