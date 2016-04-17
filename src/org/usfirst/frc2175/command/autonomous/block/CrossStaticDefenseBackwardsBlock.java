package org.usfirst.frc2175.command.autonomous.block;

import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossStaticDefenseBackwardsBlock extends CommandGroup {
    public CrossStaticDefenseBackwardsBlock(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                -135));
    }
}
