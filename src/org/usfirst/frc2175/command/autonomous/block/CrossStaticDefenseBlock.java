package org.usfirst.frc2175.command.autonomous.block;

import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossStaticDefenseBlock extends CommandGroup {
    public CrossStaticDefenseBlock(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers, boolean goingForwards) {
        if (goingForwards) {
            addSequential(new DriveUpToOuterworksBlock(robotSubsystems,
                    robotControllers));
            addSequential(new DriveInchesCommand(robotSubsystems,
                    robotControllers, 105), 6);
        } else {
            addSequential(new DriveInchesCommand(robotSubsystems,
                    robotControllers, -135));
        }
    }
}
