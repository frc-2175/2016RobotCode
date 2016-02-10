package org.usfirst.frc2175.command.group;

import org.usfirst.frc2175.command.single.RunRollerbarIntakeAtSpeedCommand;
import org.usfirst.frc2175.config.IntakeConfig;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RunIntakeOutGroup extends CommandGroup {

    public RunIntakeOutGroup(RobotSubsystems robotSubsystems,
            IntakeConfig intakeConfig) {
        double rollerbarSpeed = intakeConfig.getRollerbarSpeedOut();

        addSequential(new RunRollerbarIntakeAtSpeedCommand(robotSubsystems,
                rollerbarSpeed));

    }
}
