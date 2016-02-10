package org.usfirst.frc2175.command.group;

import org.usfirst.frc2175.command.single.RunRollerbarIntakeAtSpeedCommand;
import org.usfirst.frc2175.config.IntakeConfig;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RunIntakeInGroup extends CommandGroup {

    public RunIntakeInGroup(RobotSubsystems robotSubsystems,
            IntakeConfig intakeConfig) {
        double rollerbarSpeed = intakeConfig.getRollerbarSpeedIn();

        addSequential(new RunRollerbarIntakeAtSpeedCommand(robotSubsystems,
                rollerbarSpeed));
    }
}
