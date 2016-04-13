package org.usfirst.frc2175.command.autonomous.block;

import org.usfirst.frc2175.command.EmptyCommand;
import org.usfirst.frc2175.command.single.RunIntakeLiftAtSpeedCommand;
import org.usfirst.frc2175.command.single.RunRollerbarIntakeAtSpeedCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PutBallInCorrectPlacement extends CommandGroup {
    public PutBallInCorrectPlacement(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        double liftIntakeSpeed = -.5;
        // Lower intake
        addSequential(new RunIntakeLiftAtSpeedCommand(robotSubsystems,
                liftIntakeSpeed), .8);

        addSequential(new EmptyCommand(), .4);
        // Spin Intake In
        addSequential(new RunRollerbarIntakeAtSpeedCommand(robotSubsystems, 1),
                .25);
    }
}
