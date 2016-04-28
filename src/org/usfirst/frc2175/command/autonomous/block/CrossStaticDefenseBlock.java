package org.usfirst.frc2175.command.autonomous.block;

import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossStaticDefenseBlock extends CommandGroup {
    public CrossStaticDefenseBlock(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers, boolean goingForwards) {
        double moveAmount = 12 * 11;
        if (!goingForwards) {
            moveAmount = -moveAmount;
        }
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                moveAmount), 6);
    }
}
