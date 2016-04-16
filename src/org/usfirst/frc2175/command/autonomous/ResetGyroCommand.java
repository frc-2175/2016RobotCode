package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ResetGyroCommand extends CommandGroup {
    public ResetGyroCommand(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        PowertrainSubsystem powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
        powertrainSubsystem.resetGyro();
    }
}
